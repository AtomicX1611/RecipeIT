package com.weather.search.screens.recipe_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun RecipeDetailScreen(modifier: Modifier = Modifier,recipeDetailViewModel: RecipeDetailViewModel){
    val uiState = recipeDetailViewModel.uiState.collectAsState()
    val customBlue = Color(0xFFCCDEEC)
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 4.dp)
    ){
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = customBlue
            ),
            modifier = Modifier.fillMaxWidth()
        ){
            AsyncImage(
                model = uiState.value.data?.strMealThumb
                , contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .height(140.dp)
                    .padding(top = 6.dp)
            )

            Spacer(modifier = Modifier.height(4.dp).fillMaxWidth())

            Text(
                text = uiState.value.data?.strMeal.toString(),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 8.dp,top = 4.dp, bottom = 8.dp),
                fontSize = 20.sp
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ){
                item {
                    Text(
                        text = uiState.value.data?.strInstructions.toString(),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 8.dp,top = 4.dp)
                    )
                }
            }
        }

    }
}