package org.jmailen.nebula

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object serviceMetadata {
    val name = "Nebula"
    val version : String? = System.getenv("VERSION")

    fun show(args: Array<String>) {
        println("${serviceMetadata.name} version ${serviceMetadata.version?:"unknown"} executed with args:")
        args.forEachIndexed { i, s ->
            println("arg $i: $s")
        }
    }
}

@SpringBootApplication
open class Service

fun main(args: Array<String>) {
    serviceMetadata.show(args)
    SpringApplication.run(Service::class.java, *args)
}
