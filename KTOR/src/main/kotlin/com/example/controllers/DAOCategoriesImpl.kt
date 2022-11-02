package com.example.controllers

import com.example.dao.DatabaseCreator.databaseQuery
import com.example.models.Categories
import com.example.models.CategoriesData
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOCategoriesImpl : DAOCategories {
    private fun categoryRow(row: ResultRow) = CategoriesData(

        id = row[Categories.id],
        industry = row[Categories.industry],
        company = row[Categories.company],
        tax = row[Categories.tax]

    )

    override suspend fun getEveryCategory(): List<CategoriesData> = databaseQuery {
        Categories.selectAll().map(::categoryRow)
    }

    override suspend fun getSingleCategory(id: Int): CategoriesData? = databaseQuery {
        Categories
            .select { Categories.id eq id }
            .map(::categoryRow)
            .singleOrNull()
    }

    override suspend fun addACategory(industry: String, company: String, tax: String): CategoriesData? = databaseQuery {

        val insertStatement = Categories.insert {
            it[Categories.industry] = industry
            it[Categories.company] = company
            it[Categories.tax] = tax
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::categoryRow)

    }

    override suspend fun editCategory(id: Int, industry: String, company: String, tax: String): Boolean = databaseQuery{
        Categories.update({ Categories.id eq id }) {
            it[Categories.industry] = industry
            it[Categories.company] = company
            it[Categories.tax] = tax
        } > 0
    }

    override suspend fun deleteCategory(id: Int): Boolean = databaseQuery {
        Categories.deleteWhere { Categories.id eq id } > 0
    }

}
val daoCategory: DAOCategories = DAOCategoriesImpl().apply {
    runBlocking {
        if (getEveryCategory().isEmpty()) {
            addACategory("car", "BMW", "10000$")
            addACategory("IT", "Apple", "22222222$")
        }
    }
}