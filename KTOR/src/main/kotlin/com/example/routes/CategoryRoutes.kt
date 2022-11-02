package com.example.routes

import com.example.controllers.daoCategory
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.categoryRouting() {
    route("/categories") {

        get {
            call.respond(mapOf("categories" to daoCategory.getEveryCategory()))
        }

        get("getCategory/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respond(mapOf("category" to daoCategory.getSingleCategory(id)))
        }

        post ("addCategory") {
            val industry = call.parameters.getOrFail("industry")
            val company = call.parameters.getOrFail("company")
            val tax = call.parameters.getOrFail("tax")
            daoCategory.addACategory(industry, company, tax)
            call.respondRedirect("/categories")
        }

        put("updateCategory/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val industry = call.parameters.getOrFail("industry")
            val company = call.parameters.getOrFail("company")
            val tax = call.parameters.getOrFail("tax")
            daoCategory.editCategory(id, industry, company, tax)
            call.respondRedirect("/categories")
        }

        delete("deleteCategory/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            daoCategory.deleteCategory(id)
            call.respondRedirect("/categories")
        }
    }
}