package com.blog

import com.blog.data.model.Article
import com.mongodb.ConnectionString
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object MongoDb {
    private val connectionString = ConnectionString("mongodb+srv://yasemin:K0tl1n2023.@cluster0.gb963.mongodb.net/?retryWrites=true&w=majority")
    private val client = KMongo.createClient(connectionString).coroutine
    private val database = client.getDatabase("yourBlog")
    val articles = database.getCollection<Article>()
}