package com.example.kuitandroidapiexample.data.repository

import com.example.kuitandroidapiexample.data.dto.request.RequestDto
import com.example.kuitandroidapiexample.data.service.AnimalService

class AnimalRepository(
    private val animalService: AnimalService
) {
    suspend fun getAnimal(id: Int) = runCatching { animalService.getAnimal(id) }
    suspend fun deleteAnimal(id: Int) = runCatching { animalService.deleteAnimal(id) }
    suspend fun getTotalAnimal() = runCatching { animalService.getTotalAnimalList() }
    suspend fun registerAnimal(request: RequestDto) = runCatching { animalService.registerAnimal(request) }
}