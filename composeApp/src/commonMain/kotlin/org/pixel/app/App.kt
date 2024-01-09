package org.pixel.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pixel.app.domain.repository.Repository
import org.pixel.app.presentation.MainViewModel
import org.pixel.app.theme.AppTheme
import org.pixel.app.theme.LocalThemeIsDark
import org.pixel.app.ui.screen.HomeScreen

@Composable
internal fun App() = AppTheme {
    val repository = remember { Repository() }
    val viewModel = remember { MainViewModel(repository) }

    Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "PixelWavKMP",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1.0f))

            var isDark by LocalThemeIsDark.current
            IconButton(
                onClick = { isDark = !isDark }
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp).size(20.dp),
                    imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = null
                )
            }
        }

        HomeScreen(viewModel)

    }
}

internal expect fun openUrl(url: String?)