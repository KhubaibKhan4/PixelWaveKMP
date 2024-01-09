package org.pixel.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.pixel.app.domain.usecase.ResultState
import org.pixel.app.presentation.MainViewModel
import org.pixel.app.ui.components.DisplayResponse
import org.pixel.app.ui.components.ErrorBox
import org.pixel.app.ui.components.LoadingBox

@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    var prompt by remember { mutableStateOf("") }
    val state by viewModel.textToImage.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            when (state) {
                is ResultState.LOADING -> {
                    if (isLoading) {
                        LoadingBox()
                    }
                }

                is ResultState.SUCCESS -> {
                    val response = (state as ResultState.SUCCESS).data
                    DisplayResponse(response)

                }

                is ResultState.ERROR -> {
                    val error = (state as ResultState.ERROR).error
                    ErrorBox(error)
                }
            }
        }
        OutlinedTextField(
            value = prompt,
            onValueChange = {
                prompt = it
            },
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .padding(12.dp),
            maxLines = 1,
            enabled = true,
            placeholder = {
                Text(text = "Write Prompt.....")
            },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.getTextToImage(prompt)
                    isLoading = true
                }) {
                    Icon(Icons.Filled.Search, contentDescription = null)
                }
            }
        )
    }

}