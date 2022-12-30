package com.example.controllers

import com.example.dao.DatabaseCreator
import com.example.models.UserDisplays
import com.example.models.UserDisplaysData
import com.example.models.Users
import com.example.models.UsersData
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOUserDisplaysImpl : DAOUserDisplays {

    private fun userDisplayRow(row: ResultRow) = UserDisplaysData(
        userId = row[UserDisplays.userId],
        username = row[UserDisplays.username],
        password = row[UserDisplays.password],
        token = row[UserDisplays.token]
    )

    override suspend fun getEveryUserDisplay(): List<UserDisplaysData> = DatabaseCreator.databaseQuery {
        UserDisplays.selectAll().map(::userDisplayRow)
    }

    override suspend fun addUserDisplay(username: String, password: String, token: String): UserDisplaysData?
    = DatabaseCreator.databaseQuery {
        val insertStatement = UserDisplays.insert {
            it[UserDisplays.username] = username
            it[UserDisplays.password] = password
            it[UserDisplays.token] = token
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::userDisplayRow)
    }

    override suspend fun getSingleUserDisplayByUsername(username: String): UserDisplaysData? = DatabaseCreator.databaseQuery {
        UserDisplays
            .select{ UserDisplays.username eq username }
            .map(::userDisplayRow)
            .singleOrNull()
    }
}
val daoUserDisplay: DAOUserDisplays = DAOUserDisplaysImpl().apply {
    runBlocking {
    }
}