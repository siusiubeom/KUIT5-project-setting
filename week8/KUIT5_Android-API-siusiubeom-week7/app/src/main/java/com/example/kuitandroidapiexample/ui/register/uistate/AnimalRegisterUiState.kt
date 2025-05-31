package com.example.kuitandroidapiexample.ui.register.uistate

import com.example.kuitandroidapiexample.ui.model.AnimalType

data class AnimalRegisterUiState(
    val id:Int,
    val url: String = "",
    val animalName: String = "",
    val animalType: AnimalType = AnimalType.PROTECT,
    val address: String = "",
    val reporterName: String = "",
    val isPost: Boolean = false
)