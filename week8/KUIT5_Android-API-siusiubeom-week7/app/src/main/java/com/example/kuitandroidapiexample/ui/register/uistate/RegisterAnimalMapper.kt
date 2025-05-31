package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.data.dto.request.RequestAddAnimalDto
import com.example.kuitandroidapiexample.ui.detail.uistate.toUiState

fun RequestAddAnimalDto.toUiState() = AnimalRegisterUiState(
    id = this.data.id,
    url = this.data.url,
    animalName = this.data.breed,
    animalType = this.data.state,
    address = this.data.address,
    reporterName = this.data.name
)
