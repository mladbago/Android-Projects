package com.example.controllers

import com.example.models.UserDisplaysData
import com.example.models.UsersData

interface DAOUserDisplays {
    suspend fun getEveryUserDisplay(): List<UserDisplaysData>

    suspend fun addUserDisplay(username: String, password: String, token: String): UserDisplaysData?

    suspend fun getSingleUserDisplayByUsername(username: String): UserDisplaysData?
}