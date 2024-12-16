package com.weather.domain.model

data class DomainModel(
    val idMeal: String?,
    val strArea: String?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strCategory: String?,
    val strTags: String?,
    val strYoutube: String?,
    val strInstructions: String?,
)

data class RecipeDetails(
    val idMeal: String?,
    val strArea: String?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strCategory: String?,
    val strTags: String?,
    val strYoutube: String?,
    val strInstructions: String?,
    val ingredientsPair : List<Pair<String,String>>
)