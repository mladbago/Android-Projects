package com.example

import com.example.authentication.authenticationRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.dao.*
import io.ktor.server.config.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseCreator.init()
    configureSerialization()
    configureRouting()
    configureAuthentication()
    authenticationRouting()

}