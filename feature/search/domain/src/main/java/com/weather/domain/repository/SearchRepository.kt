package com.weather.domain.repository

import com.weather.domain.model.DomainModel
import com.weather.domain.model.RecipeDetails

interface SearchRepository {

    suspend fun getRecipes(s : String) : List<DomainModel>

    suspend fun getRecipeDetail(id : String) : RecipeDetails

}