package com.example.controllers

import com.example.dao.DatabaseCreator
import com.example.models.Users
import com.example.models.UsersData
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class DAOUsersImpl : DAOUsers {

    private fun userRow(row: ResultRow) = UsersData(

        userId = row[Users.userId],
        username = row[Users.username],
        password = row[Users.password]

    )
    override suspend fun getEveryUser(): List<UsersData> = DatabaseCreator.databaseQuery {
        Users.selectAll().map(::userRow)
    }

    override suspend fun getSingleUser(userId: Int): UsersData? = DatabaseCreator.databaseQuery {
        Users
            .select { Users.userId eq userId }
            .map(::userRow)
            .singleOrNull()
    }

    override suspend fun getSingleUserByUsername(username: String): UsersData? = DatabaseCreator.databaseQuery {
        Users
            .select{ Users.username eq username }
            .map(::userRow)
            .singleOrNull()
    }

    override suspend fun addUser(username: String, password: String): UsersData? = DatabaseCreator.databaseQuery {
            val insertStatement = Users.insert {
                it[Users.username] = username
                it[Users.password] = password
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::userRow)
    }

    override suspend fun editUser(userId: Int, username: String, password: String): Boolean = DatabaseCreator.databaseQuery {
        Users.update({ Users.userId eq userId }) {
            it[Users.username] = username
            it[Users.password] = password
        } > 0
    }

    override suspend fun deleteUser(userId: Int): Boolean = DatabaseCreator.databaseQuery {
        Users.deleteWhere { Users.userId eq userId } > 0
    }
}
val daoUser: DAOUsers = DAOUsersImpl().apply {
    runBlocking {
        if (getEveryUser().isEmpty()) {
            addUser("user", "password")
        }
    }
}