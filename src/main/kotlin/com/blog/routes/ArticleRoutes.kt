package com.blog.routes

import com.blog.data.MongoDb
import com.blog.data.createArticle
import com.blog.data.deleteArticle
import com.blog.data.getArticle
import com.blog.data.getArticles
import com.blog.data.model.CreateRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val articles = MongoDb.articles
fun Route.articleRouting() {
    route("articles") {
        get {
            call.respond(articles.getArticles())
        }

        post {
            try {
                val article = call.receive<CreateRequest>()

                val created = articles.createArticle(article)
                if (created) {
                    call.respondText("Article created successfully", status = HttpStatusCode.Created)
                } else {
                    throw Exception()
                }
            } catch (ex: Exception) {
                call.respondText("Server Error", status = HttpStatusCode.InternalServerError)
            }
        }

        get("{id}") {
            try {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing id",
                    status = HttpStatusCode.BadRequest
                )
                val article = articles.getArticle(id.toString()) ?: return@get call.respondText(
                    "No article with id $id",
                    status = HttpStatusCode.NotFound
                )
                call.respond(article)
            } catch (ex: Exception) {
                call.respondText("Server Error", status = HttpStatusCode.InternalServerError)
            }
        }

        delete("{id}") {
            try {
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "Missing id",
                    status = HttpStatusCode.BadRequest
                )
                val deleted = articles.deleteArticle(id.toString())
                if (deleted) {
                    call.respondText(
                        "Article with id $id deleted successfully",
                        status = HttpStatusCode.OK
                    )
                } else {
                    call.respondText(
                        "No article with id $id",
                        status = HttpStatusCode.NotFound
                    )
                }
            } catch (ex: Exception) {
                call.respondText("Server Error", status = HttpStatusCode.InternalServerError)
            }
        }
    }
}