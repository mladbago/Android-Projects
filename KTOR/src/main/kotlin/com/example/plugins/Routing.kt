package com.example.plugins

import com.example.routes.categoryRouting
import com.example.routes.payRouting
import com.example.routes.productRouting
import com.example.routes.userRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        categoryRouting()
        productRouting()
        userRouting()
        payRouting()
    }
}