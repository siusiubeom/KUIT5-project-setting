package com.example.kuitandroidapiexample
import com.example.kuitandroidapiexample.data.ServicePool.animalService
import com.example.kuitandroidapiexample.data.repository.AnimalRepository

class AppContainer {
    fun provideApiService() = animalService

    fun provideRepository() = AnimalRepository(animalService)
}