package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class UserDisplaysData(val userId: Int, val username: String, val password: String, val token: String)

object UserDisplays: Table() {

    val userId = integer("userId").autoIncrement()
    val username = varchar("username",128)
    val password = varchar("password", 128)
    val token = varchar("token", 255)

    override val primaryKey = PrimaryKey(userId)

}