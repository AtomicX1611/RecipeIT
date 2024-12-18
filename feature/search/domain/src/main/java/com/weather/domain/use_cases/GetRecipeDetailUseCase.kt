package com.weather.domain.use_cases

import com.weather.common.utils.NetworkResult
import com.weather.domain.model.RecipeDetails
import com.weather.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
private val searchRepository: SearchRepository
){
    operator fun invoke(id : String) = flow<NetworkResult<RecipeDetails>>{
             emit(NetworkResult.Loading())
             val response = searchRepository.getRecipeDetail(id)
             if(response.isSuccess){
                 emit(NetworkResult.Success(data = response.getOrNull()))
             }else{
                 emit(NetworkResult.Error(message = response.exceptionOrNull()?.localizedMessage))
             }
    }.catch {
            emit(NetworkResult.Error(message = it.message))
    }.flowOn(Dispatchers.IO)
}