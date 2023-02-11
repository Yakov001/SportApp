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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sportapp.ui.compose.theme.SportAppTheme
import com.example.sportapp.ui.model.Resource
import com.example.sportapp.ui.model.data_classes.Fixtures
import com.example.sportapp.ui.model.data_classes.Meta
import com.example.sportapp.ui.model.data_classes.Teams

@Composable
fun SecondFragmentScreen(
    fixtures: Resource<Fixtures>
) {
    Surface(Modifier.fillMaxSize()) {
        LazyColumn {
            items(100) {
                fixtures.data?.data?.getOrNull(it)?.let { fixture ->
                    GameCard(
                        gameId = fixture.id,
                        teams = fixture.teams,
                        onClick = {},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    gameId: Int,
    teams: Teams,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Image",
                modifier = Modifier.size(width = 128.dp, height = 228.dp)
            )
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Image",
                modifier = Modifier.size(width = 128.dp, height = 228.dp)
            )
        }
    }
}

@Composable
@Preview
fun SecondFragmentScreenPreview() {
    SportAppTheme() {
        SecondFragmentScreen(
            fixtures = fakeFixtures
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SecondFragmentScreenDarkPreview() {
    SportAppTheme {
        SecondFragmentScreen(
            fixtures = fakeFixtures
        )
    }
}

val fakeFixtures = Resource.Success(
    data = Fixtures(
        data = listOf(),
        meta = Meta(1, null, 1, 1, "", 1, 1, " ")
    )
)