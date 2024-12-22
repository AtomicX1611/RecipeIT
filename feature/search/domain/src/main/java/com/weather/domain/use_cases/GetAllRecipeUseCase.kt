package com.weather.domain.use_cases

import android.net.NetworkRequest
import android.util.Log
import com.weather.common.utils.NetworkResult
import com.weather.domain.model.DomainModel
import com.weather.domain.model.RecipeDetails
import com.weather.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllRecipeUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(q : String) = flow<NetworkResult<List<DomainModel>>>{
        Log.v("TAGy","Use-case called")
          emit(NetworkResult.Loading())
          val response = searchRepository.getRecipes(q)

          if(response.isSuccess){
              Log.v("TagY","ues case got success")
              emit(NetworkResult.Success(data = response.getOrThrow()))
          }else{
              Log.v("TagY","ues case got failure")
              emit(NetworkResult.Error(message = response.exceptionOrNull()?.localizedMessage))
          }
    }.catch {
        Log.v("TagY","ues case got exception")
        emit(NetworkResult.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)

}