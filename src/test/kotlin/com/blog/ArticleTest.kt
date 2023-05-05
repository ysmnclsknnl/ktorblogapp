package com.blog

import com.blog.data.model.CreateRequest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*


class ArticleTest : StringSpec ({
        "Creates An Article" {
            val client: HttpClient = ApplicationTestBuilder().createClient {
                    install(ContentNegotiation) { json() }
                }
            val response = client.post("/articles") {
                contentType(ContentType.Application.Json)
                setBody(CreateRequest(title= "either", body = "hello world"))
            }
            response.status shouldBe  HttpStatusCode.OK
        }
    })