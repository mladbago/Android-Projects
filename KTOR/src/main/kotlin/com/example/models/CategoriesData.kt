package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class CategoriesData(val id: Int, val industry: String, val company: String, val tax: String)

object Categories: Table() {

    val id = integer("id").autoIncrement()
    val industry = varchar("industry", 66)
    val company = varchar("company", 77)
    val tax = varchar("tax", 44)

    override val primaryKey = PrimaryKey(id)
}