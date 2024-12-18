package com.weather.data.repository

import com.weather.data.mappers.toDomain
import com.weather.data.model.RecipeDTO
import com.weather.data.remote.SearchServiceApi
import com.weather.domain.model.DomainModel
import com.weather.domain.model.RecipeDetails
import com.weather.domain.repository.SearchRepository

class SearchRepositoryImp(
    private val searchServiceApi: SearchServiceApi
) : SearchRepository {

    override suspend fun getRecipes(s : String): Result<List<DomainModel>> {
       val response = searchServiceApi.getRecipes(s)
       return if(response.isSuccessful) {
           response.body()?.meals?.let {
               Result.success(it.toDomain())
           } ?: run {
               Result.failure(Exception("Error occured"))
           }
        }else{
            Result.failure(Exception("Couldn't generate Domain models"))
        }
    }

    override suspend fun getRecipeDetail(id: String): Result<RecipeDetails> {
        val response = searchServiceApi.getRecipeDetails(id)
        return if(response.isSuccessful){
            response.body()?.meals?.let {
                if(it.isNotEmpty()){
                    Result.success(it.first().toDomain())
                }else{
                    Result.failure(Exception("Error Occured while retreiving"))
                }
                Result.success(it.first().toDomain())
            } ?: run {
                Result.failure(Exception("Error occurred"))
            }
        }else{
            Result.failure(Exception("Exception occurred"))
        }
        TODO("Not yet implemented")
    }

}

