package com.example.eui.day4

import retrofit2.Call
import retrofit2.http.GET

interface SimpleService {

    @GET("products")
    suspend fun getProductsResponse(): ProductsResponse
}