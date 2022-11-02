package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class ProductsData(val id: Int, val title: String, val price: String, val label: String, val category: Int)

object Products: Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 50)
    val price = varchar("price", 30)
    val label = varchar("label", 20)

    val category = integer("category").references(Categories.id)
    override val primaryKey = PrimaryKey(id)

}