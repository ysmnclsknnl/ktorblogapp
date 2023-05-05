package com.blog

import com.blog.data.model.CreateRequest
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testCreateArticle() = testApplication {
        val client = createClient {
            install(ContentNegotiation) { json() }
        }

        val response = client.post("/articles") {
            contentType(ContentType.Application.Json)
            setBody(CreateRequest(title= "try", body = "hello world"))
        }
//        assertEquals("Article created successfully", response.bodyAsText())
        assertEquals(HttpStatusCode.Created, response.status)
    }
}
