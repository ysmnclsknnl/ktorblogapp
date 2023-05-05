@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.blog.models

import com.blog.core.OffsetDateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.OffsetDateTime
import java.util.concurrent.atomic.AtomicInteger

@Serializable
data class Article(
    var id: String? = null,
    val title: String,
    val body: String,
    val created: OffsetDateTime,
    val updated: OffsetDateTime? = null
) {
    companion object {
        private val idCounter = AtomicInteger()

        fun newEntry(title: String, body: String, created: OffsetDateTime, updated: OffsetDateTime? = null) =
            Article(idCounter.getAndIncrement(), title, body, created, updated)
    }
}

val articles = mutableListOf(
    Article.newEntry(
        "The drive to develop!",
        "...it's what keeps me going.",
        OffsetDateTime.now()
    )
)
