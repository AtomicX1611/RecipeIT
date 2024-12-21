package com.weather.search.screens.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.weather.domain.model.DomainModel
import com.weather.search.screens.recipe_detail.RecipeDetailViewModel

@Composable
fun RecipeCard(modifier: Modifier = Modifier , recipeDetails: DomainModel,onRecipeClick : (String?) -> Unit){

    Card(modifier = modifier
        .padding(horizontal = 6.dp, vertical = 4.dp)
        .height(120.dp)
        .fillMaxWidth()
        .clickable {
            onRecipeClick(recipeDetails.idMeal)
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ){
             Row(
                 modifier = Modifier.fillMaxSize(),
                 horizontalArrangement = Arrangement.SpaceEvenly
             ){
                     RecipeImage(modifier = Modifier.weight(0.4f),recipeDetails)
                     RecipeInfo(modifier = Modifier.weight(0.6f),recipeDetails)
             }
    }
}

@Composable
fun RecipeInfo(modifier: Modifier = Modifier, recipeDetails: DomainModel) {
    Text(
        text = "${recipeDetails.strMeal}",
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 2,
        modifier = modifier
            .padding(start = 6.dp, end = 4.dp, top = 2.dp),
        fontSize = 16.sp,
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun RecipeImage(modifier: Modifier = Modifier,recipeDetails: DomainModel) {
    AsyncImage(
        model = recipeDetails.strMealThumb,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
