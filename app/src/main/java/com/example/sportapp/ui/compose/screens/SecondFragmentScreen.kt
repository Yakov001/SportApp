package com.example.sportapp.ui.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sportapp.ui.compose.theme.SportAppTheme

@Composable
fun SecondFragmentScreen() {
    Surface(Modifier.fillMaxSize()) {
        LazyColumn {
            items(100) {
                GameCard(
                    gameId = it,
                    onClick = {},
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    gameId : Int,
    onClick : () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(Modifier.padding(8.dp)) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Image",
                modifier = Modifier.size(width = 128.dp, height = 228.dp)
            )
            Column() {
                Text(text = "Match $gameId", style = MaterialTheme.typography.titleMedium)
                Text(text = stringResource(id = com.example.sportapp.R.string.lorem_ipsum))
            }
        }
    }
}

@Composable
@Preview
fun SecondFragmentScreenPreview() {
    SportAppTheme() {
        SecondFragmentScreen()
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SecondFragmentScreenDarkPreview() {
    SportAppTheme{
        SecondFragmentScreen()
    }
}