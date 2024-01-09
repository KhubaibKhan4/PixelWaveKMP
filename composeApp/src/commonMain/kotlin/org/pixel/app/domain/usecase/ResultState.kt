package org.pixel.app.domain.usecase

import org.pixel.app.data.model.TextToImage

sealed class ResultState {
    object LOADING : ResultState()
    data class SUCCESS(val data: TextToImage) : ResultState()
    data class ERROR(val error: String) : ResultState()
}