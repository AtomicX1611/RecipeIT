package com.weather.domain.model

data class DomainModel(
    val idMeal: String? = null,
    val strArea: String? = null,
    val strMeal: String? = null,
    val strMealThumb: String? = null,
    val strCategory: String? = null,
    val strTags: String? = null,
    val strYoutube: String? = null,
    val strInstructions: String? = null,
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