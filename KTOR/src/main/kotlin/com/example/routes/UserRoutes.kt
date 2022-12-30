package com.example.routes

import com.example.controllers.daoCategory
import com.example.controllers.daoProduct
import com.example.controllers.daoUser
import com.example.controllers.daoUserDisplay
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.userRouting() {
    route("/users") {

        get {
            call.respond(daoUserDisplay.getEveryUserDisplay())
        }

    }
}