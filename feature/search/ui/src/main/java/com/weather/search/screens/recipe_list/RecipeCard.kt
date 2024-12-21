package com.weather.search.screens.recipe_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.weather.domain.model.DomainModel
import com.weather.domain.model.RecipeDetails

@Composable
fun RecipeCard(modifier: Modifier = Modifier , recipeDetails: DomainModel) {
    Card(modifier = Modifier
        .padding(horizontal = 12.dp, vertical = 4.dp)
        .height(80.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
            focusedElevation = 24.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
             Row(
                 modifier = Modifier.fillMaxSize(),
                 horizontalArrangement = Arrangement.SpaceBetween
             ){
                     AsyncImage(
                         model = recipeDetails.strMealThumb,
                         contentDescription = null,
                         contentScale = ContentScale.Crop,
                         modifier = Modifier
                             .width(40.dp)
                             .height(50.dp)
                     )
                      Spacer(modifier = Modifier.height(12.dp))

                     Text(
                         text = "${recipeDetails.strMeal}",
                         style = MaterialTheme.typography.bodyMedium
                     )
             }
    }
}

@Composable
fun RecipeImage(modifier: Modifier = Modifier) {

}

@Composable
fun RecipeInfo(modifier: Modifier = Modifier) {

}