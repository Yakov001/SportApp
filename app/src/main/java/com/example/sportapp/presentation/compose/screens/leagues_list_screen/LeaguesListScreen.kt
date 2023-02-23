package com.example.sportapp.presentation.compose.screens.leagues_list_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.sportapp.domain.Resource
import com.example.sportapp.domain.entities.Game
import com.example.sportapp.presentation.compose.theme.SportAppTheme

@Composable
fun LeaguesListScreen(
    gamesList: Resource<List<List<Game>>>,
    savedGames: List<Game>,
    onGameClick: (Game) -> Unit
) {
    LazyColumn {
        // Saved Matches
        if (savedGames.isNotEmpty()) {
            item {
                LeagueCard(
                    headerName = "Saved Games",
                    games = savedGames,
                    onGameClick = onGameClick
                )
            }
        }
        // Matches By League from network
        if (gamesList is Resource.Success) {
            items(items = gamesList.data!!) { games ->
                val league = games[0].league
                LeagueCard(
                    headerName = league.name,
                    headerImage = league.country_flag,
                    games = games,
                    onGameClick = onGameClick
                )
            }
        }
    }
}

@Composable
fun LeagueCard(
    headerName: String = "Saved Games",
    headerImage: String? = null,
    games: List<Game>?,
    onGameClick: (Game) -> Unit
) {
    ElevatedCard(modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(horizontal = 16.dp)
        ) {
            SubcomposeAsyncImage(
                model = headerImage,
                contentDescription = "League Country Flag",
                error = {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = "League Icon",
                        tint = Color.Yellow
                    )
                },
                modifier = Modifier
                    .size(25.dp)
                    .padding(end = 8.dp)
            )
            Text(text = headerName.uppercase())
        }
        Column(Modifier.fillMaxWidth()) {
            games?.let {
                repeat(games.size) { i ->
                    Divider(color = MaterialTheme.colorScheme.outline)
                    LeagueGameItem(
                        home = games[i].homeTeam,
                        away = games[i].awayTeam,
                        round = games[i].roundName,
                        time = games[i].time,
                        onClick = { onGameClick(games[i]) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
fun LeagueCardPreviewDark() {
    SportAppTheme {
        Surface {
            LeagueCard(games = null, onGameClick = {})
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LeagueCardPreview() {
    SportAppTheme {
        Surface {
            LeagueCard(games = null, onGameClick = {})
        }
    }
}
