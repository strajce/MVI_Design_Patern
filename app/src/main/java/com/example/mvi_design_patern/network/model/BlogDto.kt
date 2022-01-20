package com.example.mvi_design_patern.network.model

import com.google.gson.annotations.SerializedName

data class BlogDto(
    @SerializedName("pk")
    var id: Int,

    var title: String,

    var body: String,

    var image: String,

    var category: String,
)