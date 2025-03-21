package org.akrck02

import io.ktor.server.application.*
import org.akrck02.route.configureRouting

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
}
