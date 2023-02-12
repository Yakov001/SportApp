package com.example.sportapp.ui.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sportapp.utils.Resource
import com.example.sportapp.model.data_classes.*
import com.example.sportapp.ui.compose.common_composables.AutosizeText

@Composable
fun SecondFragmentScreen(
    fixtures: Resource<Fixtures>,
    onGameClick: (Data) -> Unit
) {
    Surface(Modifier.fillMaxSize()) {
        LazyColumn {
            items(100) {
                fixtures.data?.data?.getOrNull(it)?.let { fixture ->
                    GameCard(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        teams = fixture.teams,
                        league = fixture.league.name,
                        stage = fixture.stage_name,
                        date = fixture.time.datetime.dropLast(3),
                        onClick = { onGameClick(fixture) }
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
    league: String,
    stage: String,
    teams: Teams,
    date: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
                Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
                Text(text = date, modifier = Modifier.padding(start = 16.dp))
            }
            Divider(
                color = MaterialTheme.colorScheme.outline
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            TeamMiniCard(
                imageUrl = teams.home.img,
                teamName = teams.home.name,
                status = "Home",
                modifier = Modifier.weight(1f)
            )
            MatchShortInfo(
                league = league,
                stage = stage,
                modifier = Modifier.weight(1f)
            )
            TeamMiniCard(
                imageUrl = teams.away.img,
                teamName = teams.away.name,
                status = "Away",
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Composable
fun MatchShortInfo(
    league: String,
    stage: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(text = "League:", style = MaterialTheme.typography.titleMedium)
        Text(text = league, modifier = Modifier.padding(bottom = 8.dp))

        Text(text = "Stage:", style = MaterialTheme.typography.titleMedium)
        Text(text = stage, modifier = Modifier.padding(bottom = 8.dp))
    }
}

@Composable
fun TeamMiniCard(
    imageUrl: String,
    teamName: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "${status.capitalize()} Team",
            modifier = Modifier
                .size(width = 64.dp, height = 64.dp)
                .padding(top = 8.dp),
            contentScale = ContentScale.Fit
        )
        AutosizeText(
            text = teamName,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}