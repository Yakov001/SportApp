package com.example.sportapp.ui.compose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sportapp.model.data_classes.fixtures.Data

@Composable
fun MatchScreen(
    match: Data,
    onBackClick: () -> Unit,
    onMatchSave: () -> Unit,
    isSaved: Boolean = false
) {
    Surface(Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.inverseSurface
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround)
                {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "Back")
                    }
                    Text(text = match.league.name)
                    IconButton(onClick = onMatchSave) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = "Bookmark",
                            modifier = Modifier.size(50.dp),
                            tint = if (isSaved) Color.Yellow else LocalContentColor.current
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp)
                ) {
                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = match.teams.home.img,
                            contentDescription = "Team Icon",
                            modifier = Modifier.size(70.dp).padding(bottom = 8.dp)
                        )
                        Text(text = match.teams.home.name)
                    }
                    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                        Text(text = "2 - 8", fontSize = 20.sp)
                    }
                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = match.teams.away.img,
                            contentDescription = "Team Icon",
                            modifier = Modifier.size(70.dp).padding(bottom = 8.dp)
                        )
                        Text(text = match.teams.away.name)
                    }
                }
            }
            Text(text = "Arena", fontSize = 40.sp, modifier = Modifier.padding(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column() {
                    Text(text = "Brøndby Stadium", style = MaterialTheme.typography.titleLarge)
                    Text(text = "Capacity: 29000")
                    Text(text = "City: Copenhagen")
                    Text(text = "Country: Denmark")
                }
                Divider(Modifier.width(16.dp))
                AsyncImage(
                    model = "https://cdn.soccersapi.com/images/soccer/venues/2763.png",
                    contentDescription = "Arena Image",
                    modifier = Modifier.size(150.dp)
                )
            }
            Text(
                text = "Match Stats",
                fontSize = 40.sp,
                modifier = Modifier.padding(16.dp)
            )

        }
    }
}