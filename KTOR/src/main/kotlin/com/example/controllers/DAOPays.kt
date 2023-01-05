package com.example.controllers

import com.example.models.CategoriesData
import com.example.models.PaysData

interface DAOPays {

    suspend fun getEveryPay(): List<PaysData>

    suspend fun getSinglePay(id: Int): PaysData?

    suspend fun addAPay(cardNumber: String, CCV: String): PaysData?

    suspend fun deletePay(id: Int): Boolean

}