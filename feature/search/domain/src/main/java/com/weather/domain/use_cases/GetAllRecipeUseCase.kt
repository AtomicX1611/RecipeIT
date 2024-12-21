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
          Log.v("CALLY","Invoked the network request with ${q}")
          emit(NetworkResult.Loading())
          val response = searchRepository.getRecipes(q)
          Log.v("Resp","${response.getOrThrow()}")
          if(response.isSuccess){
              emit(NetworkResult.Success(data = response.getOrThrow()))
              Log.v("TAGY", "${response.getOrThrow()}")
          }else{
              emit(NetworkResult.Error(message = response.exceptionOrNull()?.localizedMessage))
              Log.v("TAGY", "${response.getOrThrow()}")
          }
    }.catch {
        emit(NetworkResult.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)
}