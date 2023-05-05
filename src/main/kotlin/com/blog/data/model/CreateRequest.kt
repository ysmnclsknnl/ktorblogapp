package com.blog.data.model

import kotlinx.serialization.Serializable

@Serializable
class CreateRequest(
    val title: String,
    val body: String,
    val publish: Boolean = false,
    val category:List<String> = emptyList(),
)