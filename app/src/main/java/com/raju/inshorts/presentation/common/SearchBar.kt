package com.raju.inshorts.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raju.inshorts.R

@Composable
fun SearchBar(
    text: String = "",
    placeholder: String = stringResource(R.string.search_movies),
    isClickable: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onClearClick:()->Unit = {},
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(54.dp)
            .background(
                color = Color(0x20ffffff),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(start = 16.dp)
            .clickable(enabled = isClickable) { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = stringResource(R.string.search_icon),
            tint = Color.White,
            modifier = Modifier
                .size(20.dp)
                .clickable(enabled = isClickable) { onClick() }
        )

        TextField(
            value = text,
            enabled = !isClickable,
            onValueChange = { onValueChange(it)},
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable(enabled = isClickable) { onClick() },
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.clear_text),
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .size(18.dp)
                            .clickable {
                                onValueChange("")
                                onClearClick()
                            }

                    )
                }

            },
            shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
            colors = TextFieldDefaults.colors(
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            singleLine = true,

        )

    }
}