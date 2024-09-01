package com.example.a30daysaffirmationapp

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a30daysaffirmationapp.data.Affirmation
import com.example.a30daysaffirmationapp.data.DataSource.affirmations

@Composable
fun DaysAffirmationApp() {
    Scaffold(
        topBar = {
            DaysAffirmationTopAppBar()
        }
    ) { it ->
        LazyColumn (contentPadding = it) {
            items(affirmations) {
                AffirmationItem (
                    affirmation = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysAffirmationTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun AffirmationDescription(
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(description),
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun AffirmationItem(
    affirmation: Affirmation,
    modifier: Modifier
){
    var expanded by remember {
        mutableStateOf(false)
    }

    Card (
        modifier = modifier.clickable { expanded = !expanded }
    ) {
        Column (
            modifier = modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ){
            Text(
                text = stringResource(affirmation.dayNumber),
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier,
            )
            Text(
                text = stringResource(affirmation.title),
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
            )
            Image(
                painter = painterResource(affirmation.imageRes),
                contentDescription = stringResource(affirmation.title),
                contentScale = ContentScale.Crop,
                modifier = modifier
            )
            if(expanded)
                AffirmationDescription(
                    description = affirmation.description
                )
        }

    }

}