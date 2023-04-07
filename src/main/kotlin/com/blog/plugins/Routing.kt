package com.blog.plugins

import com.blog.routes.articleRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        articleRouting()
    }
}
