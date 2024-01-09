package org.pixel.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.client.utils.EmptyContent.status
import org.pixel.app.data.model.TextToImage

@Composable
fun DisplayResponse(image: TextToImage) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        /*// Display the status
        Text(
            text = "Status: $status",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
*/

        // Display the output images
        Text(
            text = "Output Images:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        image.output.forEach {singleImage ->
            val imageResponse: Resource<Painter> = asyncPainterResource(data = singleImage)

            KamelImage(
                resource = imageResponse,
                contentDescription = null,
                contentScale = ContentScale.None,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(24.dp))
            )
        }
    }
}