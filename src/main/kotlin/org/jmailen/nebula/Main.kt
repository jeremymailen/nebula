package org.jmailen.nebula

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object ServiceMetadata {
    val name = "Nebula"
    val version = System.getenv("VERSION") ?: "unknown"

    fun show(args: Array<String>) {
        println("${this.name} version ${this.version} executed with args:")
        args.forEachIndexed { i, s ->
            println("arg $i: $s")
        }
    }
}

@SpringBootApplication
open class Service

fun main(args: Array<String>) {
    ServiceMetadata.show(args)
    SpringApplication.run(Service::class.java, *args)
}
