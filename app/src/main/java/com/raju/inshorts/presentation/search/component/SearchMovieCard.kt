package com.raju.inshorts.presentation.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raju.inshorts.domain.model.Movie
import com.raju.inshorts.utils.Constant.BASE_IMAGE_URL
import kotlin.toString

@Composable
fun SearchMovieCard(movie: Movie, onClick: (Movie) -> Unit) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(movie) }
            .background(
                color = Color(android.graphics.Color.parseColor("#2f2f39"))
            ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        val context = LocalContext.current

        // this like a glide image loader
        AsyncImage(
            model = ImageRequest.Builder(context).data(BASE_IMAGE_URL + movie.poster_path)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = movie.title,
            modifier = Modifier.padding(start = 5.dp),
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xffffc107),
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = movie.vote_average.toString(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                ),
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}