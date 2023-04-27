package com.blog.repository

import com.blog.models.Article
import com.mongodb.ConnectionString
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

fun initializeDataBase() {
    val connectionString = ConnectionString("mongodb+srv://yasemin:K0tl1n2023.@cluster0.gb963.mongodb.net/?retryWrites=true&w=majority")
    val client = KMongo.createClient(connectionString)
    val database = client.getDatabase("yourBlog")
    val col = database.getCollection<Article>()
}

