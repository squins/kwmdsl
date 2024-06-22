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
import java.util.EnumSet
import java.util.concurrent.atomic.AtomicBoolean
import javax.servlet.DispatcherType.*
import kotlin.io.path.Path

@Suppress("unused")
class Starter(private val restartRunnable: Runnable, private val mustStop: AtomicBoolean) : Runnable {
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

        var isStoppingBecauseFilesChanged = false
        Scanner().apply {
            var isFirstScan = true
            addDirectory(Path("D:\\JST\\ontw\\webappraamwerk\\kotlin-wicket-markup-dsl\\build\\classes\\kotlin\\examples"))
            addDirectory(Path("D:\\JST\\ontw\\webappraamwerk\\kotlin-wicket-markup-dsl\\build\\classes\\kotlin\\main"))
            addListener(object : Scanner.BulkListener, Scanner.ScanCycleListener {
                override fun filesChanged(filenames: Set<String>) {
                    if (isFirstScan) {
                        isFirstScan = false
                    } else {
                        isStoppingBecauseFilesChanged = true
                        stop()
                        if (!server.isStopping && !server.isStopped) {
                            server.stop()
                            restartRunnable.run()
                        }
                    }
                }
            })
            scanInterval = 1
            isAutoStartScanning = true
            start()
        }

        while (!mustStop.get() && !isStoppingBecauseFilesChanged) {
            Thread.sleep(100L)
        }
        server.stop()
    }
}
