package org.pixel.app.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextToImage(
    @SerialName("generationTime")
    val generationTime: Double? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("meta")
    val meta: Meta,
    @SerialName("nsfw_content_detected")
    val nsfwContentDetected: String? = null,
    @SerialName("output")
    val output: List<String>,
    @SerialName("proxy_links")
    val proxyLinks: List<String>,
    @SerialName("status")
    val status: String,
    @SerialName("tip")
    val tip: String
)