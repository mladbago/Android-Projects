package com.example.controllers

import com.example.dao.DatabaseCreator
import com.example.models.Pays
import com.example.models.PaysData
import com.example.models.Products
import com.example.models.ProductsData
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOPaysImpl : DAOPays {
    private fun payRow(row: ResultRow) = PaysData(

        userPayId = row[Pays.userPayId],
        cardNumber = row[Pays.cardNumber],
        CCV = row[Pays.CCV]

    )
    override suspend fun getEveryPay(): List<PaysData> = DatabaseCreator.databaseQuery {
        Pays.selectAll().map(::payRow)
    }

    override suspend fun getSinglePay(id: Int): PaysData? = DatabaseCreator.databaseQuery {
        Pays
            .select { Pays.userPayId eq id }
            .map(::payRow)
            .singleOrNull()
    }

    override suspend fun addAPay(cardNumber: String, CCV: String): PaysData? = DatabaseCreator.databaseQuery {
        val insertStatement = Pays.insert {
            it[Pays.cardNumber] = cardNumber
            it[Pays.CCV] = CCV
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::payRow)
    }

    override suspend fun deletePay(id: Int): Boolean = DatabaseCreator.databaseQuery {
        Pays.deleteWhere { userPayId eq id } > 0
    }
}
val daoPay: DAOPays = DAOPaysImpl().apply {
    runBlocking {
    }
}