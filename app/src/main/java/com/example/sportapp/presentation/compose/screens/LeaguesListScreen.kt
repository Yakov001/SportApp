package com.example.sportapp.presentation.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sportapp.domain.Resource
import com.example.sportapp.domain.entities.Game
import com.example.sportapp.domain.entities.League
import com.example.sportapp.domain.entities.Team
import com.example.sportapp.presentation.compose.common_composables.AutosizeText

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
                LeagueGamePreview(
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

@Composable
fun LeagueGamePreview(
    home: Team,
    away: Team,
    round: String,
    time: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = home.image,
                    contentDescription = "Home Img",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 8.dp)
                )
                AutosizeText(
                    text = home.name,
                    style = LocalTextStyle.current,
                    textAlign = TextAlign.End
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(text = "Round: $round", style = MaterialTheme.typography.bodySmall)
            Text(text = time.dropLast(3))
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = away.image,
                contentDescription = "Away Img",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 8.dp)
            )
            AutosizeText(
                text = away.name,
                style = LocalTextStyle.current,
                textAlign = TextAlign.Start
            )
        }
    }
}