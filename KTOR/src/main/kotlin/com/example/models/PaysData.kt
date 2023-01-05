package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class PaysData(val userPayId: Int, val cardNumber: String, val CCV: String)

object Pays: Table() {

    val userPayId = integer("userPayId").autoIncrement()
    val cardNumber = varchar("cardNumber",128)
    val CCV = varchar("CCV", 128)

    override val primaryKey = PrimaryKey(userPayId)

}