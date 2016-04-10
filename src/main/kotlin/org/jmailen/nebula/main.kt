package org.jmailen.nebula

object service {
    val name = "Nebula"
    val version : String? = System.getenv("VERSION")
}

fun main(args: Array<String>) {
    println("${service.name} version ${service.version?:"unknown"} executed with args:")
    args.forEachIndexed { i, s ->
        println("arg $i: $s")
    }
}
