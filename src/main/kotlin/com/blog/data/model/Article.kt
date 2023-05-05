@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.blog.data.model

import com.blog.core.OffsetDateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Article(
    @BsonId
    var id: String = ObjectId().toString(),
    val title: String,
    val body: String,
    val publish: Boolean = false,
    val category:List<String> = emptyList(),
)

