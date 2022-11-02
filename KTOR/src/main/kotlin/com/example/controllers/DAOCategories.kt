package com.example.controllers

import com.example.models.CategoriesData

interface DAOCategories {

    suspend fun getEveryCategory(): List<CategoriesData>

    suspend fun getSingleCategory(id: Int): CategoriesData?

    suspend fun addACategory(industry: String, company: String, tax: String): CategoriesData?

    suspend fun editCategory(id: Int, industry: String, company: String, tax: String): Boolean

    suspend fun deleteCategory(id: Int): Boolean

}