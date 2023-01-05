package com.example.routes

import com.example.controllers.daoCategory
import com.example.controllers.daoPay
import com.example.controllers.daoProduct
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.payRouting() {
    route("/pays") {

        get {
            call.respond(daoPay.getEveryPay())
        }

        get("getPay/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respond(mapOf("pay" to daoPay.getSinglePay(id)))
        }

        post("addPay") {

            val cardNumber = call.parameters.getOrFail("cardNumber")
            val CCV = call.parameters.getOrFail("CCV")

            daoPay.addAPay(cardNumber, CCV)
            call.respondRedirect("/pays")

        }


        delete("deletePay/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            daoPay.deletePay(id)
            call.respondRedirect("/pays")
        }
    }
}