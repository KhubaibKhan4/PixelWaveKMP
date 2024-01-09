package org.pixel.app.presentation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.pixel.app.domain.repository.Repository
import org.pixel.app.domain.usecase.ResultState

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _textToImage = MutableStateFlow<ResultState>(ResultState.LOADING)
    val textToImage: StateFlow<ResultState> = _textToImage.asStateFlow()

    fun getTextToImage(prompt: String) {
        viewModelScope.launch {
            _textToImage.value = ResultState.LOADING
            try {
                val response = repository.getTextToImage(prompt)
                _textToImage.value = ResultState.SUCCESS(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _textToImage.value = ResultState.ERROR(error)
            }
        }
    }
}