package com.example.sportapp.ui.compose.screens

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
import com.example.sportapp.model.data_classes.*
import com.example.sportapp.ui.compose.common_composables.AutosizeText
import com.example.sportapp.utils.Resource

@Composable
fun LeaguesListScreen(
    listFixtures: Resource<List<Fixtures>>,
    onMatchClick: (Data) -> Unit
) {
    if (listFixtures is Resource.Success) {
        LazyColumn {
            items(items = listFixtures.data!!) { fixtures ->
                val league = fixtures.data[0].league
                LeagueCard(
                    league = league,
                    games = fixtures.data,
                    onMatchClick = onMatchClick
                )
            }
        }
    }
}

@Composable
fun LeagueCard(
    league: League,
    games: List<Data>,
    onMatchClick: (Data) -> Unit
) {
    ElevatedCard(modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)) {
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
        Column(Modifier.fillMaxWidth()) {
            repeat(games.size) { i ->
                Divider(color = MaterialTheme.colorScheme.outline)
                LeagueGamePreview(
                    home = games[i].teams.home,
                    away = games[i].teams.away,
                    round = games[i].round_name,
                    time = games[i].time.time,
                    onClick = { onMatchClick(games[i]) }
                )
            }
        }
    }
}

@Composable
fun LeagueGamePreview(
    home: Home,
    away: Away,
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
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = home.img,
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
                model = away.img,
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