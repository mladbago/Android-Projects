package com.example.dao

import com.example.models.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*


object DatabaseCreator {
    fun init() {

        val driverName = "org.sqlite.JDBC"
        val url = "jdbc:sqlite:./build/db"

        val database = Database.connect(url, driverName)
        transaction(database) {
            SchemaUtils.create(Categories, Products, Users, UserDisplays, Pays)
        }

    }
    suspend fun <T> databaseQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}