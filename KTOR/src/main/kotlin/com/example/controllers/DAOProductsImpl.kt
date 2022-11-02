package com.example.controllers

import com.example.dao.DatabaseCreator
import com.example.models.ProductsData
import com.example.models.Products
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOProductsImpl : DAOProducts {

    private fun productRow(row: ResultRow) = ProductsData(

        id = row[Products.id],
        title = row[Products.title],
        price = row[Products.price],
        label = row[Products.label],
        category = row[Products.category]

    )

    override suspend fun getEveryProduct(): List<ProductsData> = DatabaseCreator.databaseQuery {
        Products.selectAll().map(::productRow)
    }

    override suspend fun getSingleProduct(id: Int): ProductsData? = DatabaseCreator.databaseQuery {
        Products
            .select { Products.id eq id }
            .map(::productRow)
            .singleOrNull()
    }

    override suspend fun addAProduct(title: String, price: String, label: String, category: Int): ProductsData? =
        DatabaseCreator.databaseQuery {
            val insertStatement = Products.insert {
                it[Products.title] = title
                it[Products.price] = price
                it[Products.label] = label
                it[Products.category] = category
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::productRow)
        }

    override suspend fun editProduct(id: Int, title: String, price: String, label: String, category: Int): Boolean =
        DatabaseCreator.databaseQuery {
            Products.update({ Products.id eq id }) {
                it[Products.title] = title
                it[Products.price] = price
                it[Products.label] = label
                it[Products.category] = category
            } > 0
        }

    override suspend fun deleteProduct(id: Int): Boolean = DatabaseCreator.databaseQuery {
        Products.deleteWhere { Products.id eq id } > 0
    }

}
val daoProduct: DAOProducts = DAOProductsImpl().apply {
    runBlocking {
        if (getEveryProduct().isEmpty()) {
            addAProduct("BMW X6", "100000$", "BMWXSeriesV.6$", 1)
        }
    }
}