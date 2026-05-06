package kwmdsl.examples

import org.apache.wicket.protocol.http.WicketFilter
import org.eclipse.jetty.ee8.nested.SessionHandler
import org.eclipse.jetty.ee8.servlet.FilterHolder
import org.eclipse.jetty.ee8.servlet.ServletContextHandler
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.session.DefaultSessionCache
import org.eclipse.jetty.session.FileSessionDataStore
import org.eclipse.jetty.util.Scanner
import org.eclipse.jetty.util.thread.QueuedThreadPool
import java.io.File
import java.nio.file.Path
import java.util.EnumSet
import java.util.concurrent.atomic.AtomicBoolean
import javax.servlet.DispatcherType.ASYNC
import javax.servlet.DispatcherType.ERROR
import javax.servlet.DispatcherType.REQUEST
import kotlin.io.path.exists
import kotlin.io.path.toPath

@Suppress("unused")
class Starter(private val restartRunnable: Runnable, private val mustStop: AtomicBoolean) : Runnable {
    private val pathsToWatch = checkNotNull(Starter::class.java.getResource("Starter.class")).let { starterClassUrl ->
        check(starterClassUrl.protocol == "file")

        val starterClassPath = starterClassUrl.toURI().toPath()

        val examplesPackageDirectory = checkNotNull(starterClassPath.parent)
        val kwmdslPackageDirectory = checkNotNull(examplesPackageDirectory.parent)
        val examplesSourceSetClassesDirectory = checkNotNull(kwmdslPackageDirectory.parent)
        val kotlinClassesDirectory = checkNotNull(examplesSourceSetClassesDirectory.parent)

        val classesDirectory = checkNotNull(kotlinClassesDirectory.parent)
        val buildDirectory = checkNotNull(classesDirectory.parent)

        val resourcesDirectory = checkNotNull(buildDirectory.resolve("resources"))

        val mainSourceSetClassesDirectory = checkNotNull(kotlinClassesDirectory.resolve("main"))
        val examplesSourceSetResourcesDirectory = checkNotNull(resourcesDirectory.resolve("examples"))
        val mainSourceSetResourcesDirectory = checkNotNull(resourcesDirectory.resolve("main"))

        listOf(
            examplesSourceSetClassesDirectory,
            examplesSourceSetResourcesDirectory,
            mainSourceSetClassesDirectory,
            mainSourceSetResourcesDirectory,
        )
            .filter { it.exists() }
    }

    override fun run() {
        val jettyThreadPool = QueuedThreadPool()
        val wicketFilter = WicketFilter()
        val wicketFilterHolder = FilterHolder(wicketFilter).apply {
            setInitParameter("filterMappingUrlPattern", "/*")
            setInitParameter("applicationClassName", "kwmdsl.examples.KwmDslExamplesApplication")
        }
        val sessionHandler = SessionHandler().apply {
            sessionCache = DefaultSessionCache(this.sessionManager).apply {
                sessionDataStore = FileSessionDataStore().apply {
                    val baseDir = File(System.getProperty("java.io.tmpdir"))
                    this.storeDir = File(baseDir, "kwmdsl-session-store").apply { mkdir() }
                }
            }
        }
        val jettyServletContextHandler = ServletContextHandler().apply {
            contextPath = "/"
            addFilter(wicketFilterHolder, "/*", EnumSet.of(REQUEST, ERROR, ASYNC))
            this@apply.sessionHandler = sessionHandler
        }
        val server = Server(jettyThreadPool).apply {
            handler = jettyServletContextHandler.coreContextHandler
        }
        val connector = ServerConnector(server).apply {
            port = 8080
        }
        server.addConnector(connector)

        server.start()

        var isStoppingBecauseFilesChanged = AtomicBoolean()
        try {
            val scanner = Scanner().apply {
                var isFirstScan = true
                pathsToWatch.forEach { addDirectory(it) }
                addListener(object : Scanner.BulkListener, Scanner.ScanCycleListener {
                    override fun pathsChanged(pathNotifications: Map<Path, Scanner.Notification>) {
                        if (isFirstScan) {
                            isFirstScan = false
                        } else {
                            isStoppingBecauseFilesChanged.set(true)
                        }
                    }
                })
                scanInterval = 1
                isAutoStartScanning = true
                start()
            }
            try {
                while (!mustStop.get() && !isStoppingBecauseFilesChanged.get()) {
                    Thread.sleep(100L)
                }
            } finally {
                scanner.stop()
            }
        } finally {
            server.stop()
        }

        if (isStoppingBecauseFilesChanged.get()) {
            restartRunnable.run()
        }
    }
}
