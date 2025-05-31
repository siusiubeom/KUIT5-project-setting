package com.example.kuitandroidapiexample.ui.register.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.dto.request.RequestDto
import com.example.kuitandroidapiexample.data.repository.AnimalRepository
import com.example.kuitandroidapiexample.ui.detail.viewmodel.AnimalDetailViewModel
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.register.uistate.AnimalRegisterUiState
import com.example.kuitandroidapiexample.ui.register.uistate.toUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimalRegisterViewModel(
    private val animalRepository: AnimalRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(AnimalRegisterUiState(id = 1))
    val uiState = _uiState.asStateFlow()
    fun postAnimal(
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
        viewModelScope.launch {
            runCatching {
                animalRepository.registerAnimal(register).fold(
                    onSuccess = { data ->
                        _uiState.value = data.data.toUiState().copy(isPost = true)
                    },
                    onFailure = {error ->
                        Log.e("okhttpError", error.message.toString())
                    }
                )
            }

        }
    }
    class AnimalRegisterViewModelFactory(
        private val animalRepository: AnimalRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            AnimalRegisterViewModel(animalRepository) as T
    }
}

