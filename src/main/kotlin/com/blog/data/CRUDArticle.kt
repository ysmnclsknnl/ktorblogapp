package com.blog.data

import com.blog.data.model.Article
import com.blog.data.model.CreateRequest
import com.blog.data.model.UpdateRequest
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineCollection

suspend fun  CoroutineCollection<Article>.getArticles(): List<Article> = find().toList()

suspend fun CoroutineCollection<Article>.getArticle(id: String): Article? = findOneById(id)

suspend fun CoroutineCollection<Article>.createArticle(article: CreateRequest) = Article(
            id = ObjectId().toString(),
            title = article.title,
            body = article.body,
            publish = article.publish,
            category = article.category)
    .let{insertOne(it).wasAcknowledged()
}


suspend fun CoroutineCollection<Article>.updateArticle(article: UpdateRequest): Boolean {
    val articleExists = findOneById(article.id) != null

    return if (articleExists) {
        updateOneById(article.id, article).wasAcknowledged()
    } else {
        false
    }
}

suspend fun CoroutineCollection<Article>.deleteArticle(id: String): Boolean = findOneById(id)?.let { deleteOneById(id).wasAcknowledged()
} ?: false

