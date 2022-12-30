package com.example.routes

import com.example.controllers.daoCategory
import com.example.controllers.daoProduct
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.productRouting() {
    authenticate("auth-user") {
        route("/products") {
            get("/hello") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal!!.payload.getClaim("username").asString()
                val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
                call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
            }

            get {
                call.respond(mapOf("products" to daoProduct.getEveryProduct()))
            }

            get("getProduct/{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(mapOf("product" to daoProduct.getSingleProduct(id)))
            }

            post("addProduct") {

                val title = call.parameters.getOrFail("title")
                val price = call.parameters.getOrFail("price")
                val label = call.parameters.getOrFail("label")
                val category = call.parameters.getOrFail<Int>("category").toInt()

                if (daoCategory.getSingleCategory(category) != null) {
                    daoProduct.addAProduct(title, price, label, category)
                    call.respondRedirect("/products")
                } else {
                    call.respondText("Category Not Found", status = HttpStatusCode.NotFound)
                }
            }

            put("updateProduct/{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val title = call.parameters.getOrFail("title")
                val price = call.parameters.getOrFail("price")
                val label = call.parameters.getOrFail("label")
                val category = call.parameters.getOrFail<Int>("category").toInt()
                if (daoCategory.getSingleCategory(category) != null) {
                    daoProduct.editProduct(id, title, price, label, category)
                } else {
                    call.respondText("Category not found", status = HttpStatusCode.NotFound)
                }

                call.respondRedirect("/products")
            }

            delete("deleteProduct/{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                daoProduct.deleteProduct(id)
                call.respondRedirect("/products")
            }
        }
    }
}