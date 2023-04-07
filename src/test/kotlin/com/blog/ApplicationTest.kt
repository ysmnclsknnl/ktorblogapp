package com.blog

import com.blog.models.Article
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import java.time.OffsetDateTime
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testPostCustomer() = testApplication {
        val client = createClient {
            install(ContentNegotiation) { json() }
        }

        val response = client.post("/articles") {
            contentType(ContentType.Application.Json)
            setBody(Article(3, "try", "hello world", OffsetDateTime.now(), null))
        }
        assertEquals("Article created successfully", response.bodyAsText())
        assertEquals(HttpStatusCode.Created, response.status)
    }
}
