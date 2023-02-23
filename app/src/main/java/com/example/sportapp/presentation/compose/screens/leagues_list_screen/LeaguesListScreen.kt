package com.example.sportapp.presentation.compose.screens.leagues_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sportapp.domain.Resource
import com.example.sportapp.domain.entities.Game
import com.example.sportapp.domain.entities.League

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
                LeagueCard(games = savedGames, onGameClick = onGameClick)
            }
        }
        // Matches By League from network
        if (gamesList is Resource.Success) {
            items(items = gamesList.data!!) { games ->
                val league = games[0].league
                LeagueCard(
                    league = league,
                    games = games,
                    onGameClick = onGameClick
                )
            }
        }
    }
}

@Composable
fun LeagueCard(
    league: League? = null,
    games: List<Game>,
    onGameClick: (Game) -> Unit
) {
    ElevatedCard(modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)) {
        if (league != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = league.country_flag,
                    contentDescription = "League Country Flag",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 8.dp)
                )
                Text(text = league.name.uppercase())
            }
        }
        Column(Modifier.fillMaxWidth()) {
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
