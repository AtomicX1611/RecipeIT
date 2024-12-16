package com.weather.data.repository

import com.weather.data.remote.SearchServiceApi
import com.weather.domain.model.DomainModel
import com.weather.domain.model.RecipeDetails
import com.weather.domain.repository.SearchRepository

class SearchRepositoryImp(
    private val searchServiceApi: SearchServiceApi
) : SearchRepository {

    override suspend fun getRecipes(s : String): List<DomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeDetail(id: String): RecipeDetails {
        TODO("Not yet implemented")
    }

}