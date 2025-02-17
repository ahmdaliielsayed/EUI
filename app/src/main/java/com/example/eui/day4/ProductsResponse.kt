package com.example.eui.day4

data class ProductsResponse(
    val limit: Int?,
    val products: List<Product?>?,
    val skip: Int?,
    val total: Int?
)