package org.pixel.app.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.pixel.app.data.model.TextToImage
import org.pixel.app.utils.Constant.BASE_URL
import org.pixel.app.utils.Constant.KEY
import org.pixel.app.utils.Constant.TIMEOUT

object StableApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun getTextToImage(
        prompt: String
    ): TextToImage {
        val url = BASE_URL + "text2img"
        val req = buildJsonObject {
            put("key", KEY)
            put("prompt", prompt)
            put(
                "negative_prompt",
                "((out of frame)), ((extra fingers)), mutated hands, ((poorly drawn hands)), ((poorly drawn face)), (((mutation))), (((deformed))), (((tiling))), ((naked)), ((tile)), ((fleshpile)), ((ugly)), (((abstract))), blurry, ((bad anatomy)), ((bad proportions)), ((extra limbs)), cloned face, (((skinny))), glitchy, ((extra breasts)), ((double torso)), ((extra arms)), ((extra hands)), ((mangled fingers)), ((missing breasts)), (missing lips), ((ugly face)), ((fat)), ((extra legs))"
            )
            put("width", "512")
            put("height", "512")
            put("samples", "1")
            put("num_inference_steps", "20")
            put("safety_checker", "no")
            put("enhance_prompt", "yes")
            put("temp", "yes")
            put("seed", null)
            put("guidance_scale", 7.5)
            put("webhook", null)
            put("track_id", null)
        }

        return client.post {
            url(url)
            setBody(req)
            contentType(ContentType.Application.Json)
        }.body()
    }
}