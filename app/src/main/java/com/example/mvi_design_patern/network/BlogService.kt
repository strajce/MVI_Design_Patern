package com.example.mvi_design_patern.network

import com.example.mvi_design_patern.network.model.BlogDto
import retrofit2.http.GET

interface BlogService {

    @GET("blogs")
    suspend fun get(): List<BlogDto>
}