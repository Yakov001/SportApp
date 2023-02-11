package com.example.sportapp.ui.compose.common_composables

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AutosizeText(
    text: String,
    style: TextStyle,
    textAlign: TextAlign
) {

    var multiplier by remember { mutableStateOf(1f) }

    Text(
        text = text,
        maxLines = 1, // modify to fit your need
        overflow = TextOverflow.Visible,
        style = style.copy(
            fontSize = LocalTextStyle.current.fontSize * multiplier,
            textAlign = textAlign
        ),
        onTextLayout = {
            if (it.hasVisualOverflow) {
                multiplier *= 0.99f // you can tune this constant
            }
        }
    )
}