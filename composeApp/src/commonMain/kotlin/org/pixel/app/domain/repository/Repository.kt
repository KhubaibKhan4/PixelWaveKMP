package org.pixel.app.domain.repository

import org.pixel.app.data.model.TextToImage
import org.pixel.app.data.remote.StableApiClient
import org.pixel.app.domain.plugin.PixelsApi

class Repository: PixelsApi {
    override suspend fun getTextToImage(prompt: String): TextToImage {
        return StableApiClient.getTextToImage(prompt)
    }
}