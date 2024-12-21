package com.weather.data.repository

import android.util.Log
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
        Log.v("TAGGED","Repo called1")
       return try {
           Log.v("TAGGED","Repo called")
           val response = searchServiceApi.getRecipes(s)
           //This is where the error is

           Log.v("REPLY","Response : ${response.body()}")
           return if(response.isSuccessful) {
               response.body()?.meals?.let {
                   Result.success(it.toDomain())
               } ?: run {
                   Result.failure(Exception("Error occurred"))
               }
           }else{
               Result.failure(Exception("Couldn't generate Domain models"))
           }
       }catch (e : Exception){
           Log.e("TAGGED", "Network request failed", e)
           Result.failure(e)
       }
    }

    override suspend fun getRecipeDetail(id: String): Result<RecipeDetails> {
        return try {
            val response = searchServiceApi.getRecipeDetails(id)
            return if(response.isSuccessful){
                response.body()?.meals?.let {
                    if(it.isNotEmpty()){
                        Result.success(it.first().toDomain())
                    }else{
                        Result.failure(Exception("Error Occurred while retrieving"))
                    }
                    Result.success(it.first().toDomain())
                } ?: run {
                    Result.failure(Exception("Error occurred"))
                }
            }else{
                Result.failure(Exception("Exception occurred"))
            }
        }catch (e : Exception){
            Result.failure(e)
        }
    }

}

