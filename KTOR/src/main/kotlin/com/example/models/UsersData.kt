package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class UsersData(val userId: Int, val username: String, val password: String)

object Users: Table() {

    val userId = integer("userId").autoIncrement()
    val username = varchar("username",128)
    val password = varchar("password", 128)

    override val primaryKey = PrimaryKey(userId)

}