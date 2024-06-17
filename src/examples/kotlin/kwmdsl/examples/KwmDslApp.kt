package kwmdsl.examples

import org.apache.wicket.protocol.http.WicketFilter
import org.eclipse.jetty.ee8.nested.SessionHandler
import org.eclipse.jetty.ee8.servlet.FilterHolder
import org.eclipse.jetty.ee8.servlet.ServletContextHandler
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.session.DefaultSessionCache
import org.eclipse.jetty.session.FileSessionDataStore
import org.eclipse.jetty.util.thread.QueuedThreadPool
import java.io.File
import java.util.EnumSet
import javax.servlet.DispatcherType.*

fun main() {
    val jettyThreadPool = QueuedThreadPool()
    val application = KwmDslExamplesApplication()
    val wicketFilter = WicketFilter(application)
    val wicketFilterHolder = FilterHolder(wicketFilter).apply {
        setInitParameter("filterMappingUrlPattern", "/*")
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
    try {
        println("Press enter to quit")
        readln()
    } finally {
        server.stop()
    }
}
