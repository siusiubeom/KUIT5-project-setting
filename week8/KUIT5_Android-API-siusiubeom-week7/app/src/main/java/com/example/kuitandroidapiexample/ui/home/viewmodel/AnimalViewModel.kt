package com.example.kuitandroidapiexample.ui.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.data.dto.request.RequestDto
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.detail.uistate.AnimalDetailUiState
import com.example.kuitandroidapiexample.ui.detail.uistate.toUiState
import com.example.kuitandroidapiexample.ui.home.uistate.AnimalListUiState
import com.example.kuitandroidapiexample.ui.home.uistate.toUiState

import com.example.kuitandroidapiexample.ui.model.AnimalType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AnimalViewModel(
    private val animalRepository: AnimalRepository
) : ViewModel() {
    //private val animalService: AnimalService by lazy { ServicePool.animalService }


    private val _animalAddState = mutableStateOf<RequestAddAnimalDto?>(null)
    val animalAddState: State<RequestAddAnimalDto?> get() = _animalAddState

    private val _registerSuccess = mutableStateOf(false)
    val registerSuccess: State<Boolean> get() = _registerSuccess

    private val _uiState = MutableStateFlow(AnimalListUiState())
    val uiState = _uiState.asStateFlow()

    fun getTotalAnimalList() {
        viewModelScope.launch {
            animalRepository.getTotalAnimal()
                .onSuccess { baseResponse ->
                    _uiState.value = AnimalListUiState(
                        animals = baseResponse.data.map { it.toUiState() }
                    )
                }
                .onFailure { error ->
                    Log.e("getTotalAnimalList", "API call failed", error)
                }
        }
    }

    class AnimalViewModelFactory(
        private val animalRepository: AnimalRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T = AnimalViewModel(animalRepository) as T
    }


    /*fun getAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalRepository.getAnimal(id)
            }
                .onSuccess { data ->
                    _animalDetailState.value = data
                }
                .onFailure { error ->
                    Log.e("getAnimal", error.message ?: "Unknown error")
                }
        }
    }

    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                animalRepository.deleteAnimal(id)
            }
                .onSuccess { data ->
                    _animalDeleteState.value = data
                }
                .onFailure { error ->
                    Log.e("deleteAnimal", error.message ?: "Unknown error")
                }
        }
    }



    fun postAddAnimal(
        name: String,
        url: String,
        state: AnimalType,
        address: String,
        breed: String,
        id: Int

    ) {
        val register = RequestDto(
            name = name,
            url = url,
            address = address,
            state = state,
            breed = breed,
            id = id

        )
        val requestAddAnimalDto = RequestAddAnimalDto(data = register)
        viewModelScope.launch{
            runCatching {
                animalRepository.registerAnimal(requestAddAnimalDto)
            }
                .onSuccess { data ->
                    _animalAddState.value = requestAddAnimalDto
                }
                .onFailure { error ->
                    Log.e("registerAnimal", error.message ?: "Unknown error")
                }
        }
    } */

}
