package kwmdsl.examples

import java.io.File
import java.net.URLClassLoader
import java.util.concurrent.atomic.AtomicBoolean

fun main() {
    val classPathUrls = System.getProperty("java.class.path")
        .split(File.pathSeparator)
        .map { File(it).toURI().toURL() }
        .toTypedArray()
    val appClassLoaderParent = Thread.currentThread().contextClassLoader.parent
    val mustStop = AtomicBoolean()
    object : Runnable {
        override fun run() {
            val serverInstanceClassLoader = URLClassLoader(classPathUrls, appClassLoaderParent)
            var numberOfAttemptsToLoadStarterClass = 0
            var starterClass: Class<*>? = null
            while (starterClass == null && numberOfAttemptsToLoadStarterClass < 3) {
                try {
                    starterClass = serverInstanceClassLoader.loadClass("kwmdsl.examples.Starter")
                } catch (_: ClassNotFoundException) {
                    numberOfAttemptsToLoadStarterClass += 1
                    // We might be unlucky and be attempting to open the class while the build is still running.
                    // Simply wait a bit, and then try again
                    Thread.sleep(250L)
                }
            }
            if (starterClass != null) {
                val starter = starterClass.constructors[0].newInstance(this, mustStop) as Runnable
                Thread(starter).apply {
                    contextClassLoader = serverInstanceClassLoader
                }.start()
            }
        }
    }.run()

    println("Examples are running at: http://localhost:8080/")
    println("Make changes to the code, and then compile in your IDE. The web server will restart with the latest version of the classes.")
    println("Press enter to quit")
    readln()
    mustStop.set(true)
}
