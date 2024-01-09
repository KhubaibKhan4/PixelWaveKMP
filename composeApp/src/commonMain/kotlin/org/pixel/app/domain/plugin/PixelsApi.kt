package org.pixel.app.domain.plugin

import org.pixel.app.data.model.TextToImage

interface PixelsApi {
    suspend fun getTextToImage(prompt: String): TextToImage
}