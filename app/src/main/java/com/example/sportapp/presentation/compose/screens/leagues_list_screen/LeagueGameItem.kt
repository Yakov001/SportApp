package com.example.sportapp.presentation.compose.screens.leagues_list_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.R
import com.example.sportapp.domain.entities.Team
import com.example.sportapp.presentation.compose.common_composables.AutosizeText
import com.example.sportapp.presentation.compose.theme.SportAppTheme

@Composable
fun LeagueGameItem(
    home: Team,
    away: Team,
    round: String,
    time: String,
    onClick: () -> Unit
) {
    LeagueGameItem(
        homeImage = home.image,
        homeName = home.name,
        awayImage = away.image,
        awayName = away.name,
        round = round,
        time = time,
        onClick = onClick
    )
}

@Composable
private fun LeagueGameItem(
    homeImage : String?,
    homeName: String,
    awayImage: String?,
    awayName: String,

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
                Image(
                    painter = rememberAsyncImagePainter(
                        model = homeImage,
                        placeholder = painterResource(R.drawable.empty_league)
                    ),
                    contentDescription = "Home Img",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 8.dp)
                )
                AutosizeText(
                    text = homeName,
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
            Image(
                painter = rememberAsyncImagePainter(
                    model = awayImage,
                    placeholder = painterResource(R.drawable.empty_league)
                ),
                contentDescription = "Away Img",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 8.dp)
            )
            AutosizeText(
                text = awayName,
                style = LocalTextStyle.current,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun LeagueGameItemPreviewDark() {
    SportAppTheme {
        Surface {
            LeagueGameItem(
                homeImage = null,
                homeName = "Barcelona",
                awayImage = null,
                awayName = "Real Madrid",
                round = "1",
                time = "16:00",
                onClick = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LeagueGameItemPreview() {
    SportAppTheme {
        Surface {
            LeagueGameItem(
                homeImage = null,
                homeName = "Barcelona",
                awayImage = null,
                awayName = "Real Madrid",
                round = "1",
                time = "16:00",
                onClick = {}
            )
        }
    }
}