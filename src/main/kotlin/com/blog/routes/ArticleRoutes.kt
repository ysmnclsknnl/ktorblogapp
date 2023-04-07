package com.blog.routes

import com.blog.models.Article
import com.blog.models.articles
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.articleRouting() {
    route("articles") {
        get {
            call.respond(articles)
        }

        post {
            val article = call.receive<Article>()

            articles.add(article)

            call.respondText("Article created successfully", status = HttpStatusCode.Created)
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val article = articles.find { it.id == id.toInt() } ?: return@get call.respondText(
                "No article with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(article)
        }
    }
}
