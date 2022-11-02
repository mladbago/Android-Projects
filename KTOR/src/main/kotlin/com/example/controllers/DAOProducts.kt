package com.example.controllers

import com.example.models.ProductsData

interface DAOProducts {

    suspend fun getEveryProduct(): List<ProductsData>

    suspend fun getSingleProduct(id: Int): ProductsData?

    suspend fun addAProduct(title: String, price: String, label: String, category: Int): ProductsData?

    suspend fun editProduct(id: Int, title: String, price: String, label: String, category: Int): Boolean

    suspend fun deleteProduct(id: Int): Boolean

}