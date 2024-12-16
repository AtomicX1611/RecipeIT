package com.weather.data.remote

import com.weather.data.model.RecipeDetailsResponse
import com.weather.data.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchServiceApi{

    @GET("api/json/v1/1/search.php")
    suspend fun getRecipes(@Query("s") s : String) : Response<RecipeResponse>

    @GET("api/json/v1/1/lookup.php")
    suspend fun getRecipeDetails(@Query("i") i : String) : Response<RecipeDetailsResponse>

}