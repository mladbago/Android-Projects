package com.example.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.controllers.daoUser
import com.example.controllers.daoUserDisplay
import com.example.dao.DatabaseCreator
import com.example.models.Users

import com.typesafe.config.ConfigFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*

import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.jetbrains.exposed.sql.insert



suspend fun canAddUser(username: String, password: String): Boolean = DatabaseCreator.databaseQuery {
    Users.insert {
        it[Users.username] = username
        it[Users.password] = password
    } get Users.userId > 0
}

fun Application.authenticationRouting() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val secret = config.propertyOrNull("jwt.secret")?.getString() ?: "secret"

    routing {
        post("/signup") {
            val username = call.parameters.getOrFail("username")
            val password = call.parameters.getOrFail("password")

            if (daoUser.getSingleUserByUsername(username) != null)
                call.respondText("User exists", status = HttpStatusCode.BadRequest)
            else if (canAddUser(username, password))
                call.respondText("User added", status = HttpStatusCode.Created)
            else
                call.respondText("Error adding user", status = HttpStatusCode.InternalServerError)
        }
        post("/login") {
            val username = call.parameters.getOrFail("username")
            val password = call.parameters.getOrFail("password")
            if (daoUser.getSingleUserByUsername(username) == null)
                call.respondText("No such user", status = HttpStatusCode.BadRequest)
            else if (daoUser.getSingleUserByUsername(username)!!.password != password)
                call.respondText("Password and username don't match", status = HttpStatusCode.BadRequest)
            else {
                val token = JWT.create()
                    .withClaim("username", username)
                    .sign(Algorithm.HMAC256(secret))

                if (daoUserDisplay.getSingleUserDisplayByUsername(username) == null)
                    daoUserDisplay.addUserDisplay(username, password, token)
                call.respond(mapOf("token" to token))
            }
        }
    }
}