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
            var starter = serverInstanceClassLoader
                .loadClass("kwmdsl.examples.Starter")
                .constructors[0]
                .newInstance(this, mustStop) as Runnable
            Thread(starter).apply {
                contextClassLoader = serverInstanceClassLoader
            }.start()
        }
    }.run()

    println("Make changes to the code, and then compile in your IDE. The web server will restart with the latest version of the classes.")
    println("Press enter to quit")
    readln()
    mustStop.set(true)
}
