package com.example.controllers

import com.example.models.ProductsData
import com.example.models.UsersData

interface DAOUsers {
    suspend fun getEveryUser(): List<UsersData>

    suspend fun getSingleUser(userId: Int): UsersData?

    suspend fun getSingleUserByUsername(username: String): UsersData?

    suspend fun addUser(username: String, password: String): UsersData?

    suspend fun editUser(userId: Int, username: String, password: String): Boolean

    suspend fun deleteUser(userId: Int): Boolean
}