package com.ikseong.diexample

import androidx.compose.runtime.structuralEqualityPolicy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ikseong.diexample.data.repository.GithubRepository
import com.ikseong.diexample.data.service.GithubService
import com.ikseong.diexample.ui.github.viewmodel.GithubViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

class AppContainer {

    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply{
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val githubService = retrofit.create(GithubService::class.java)

    fun provideGithubSevice() = githubService
    fun provideGithubRepository() = GithubRepository(githubService)

    class GithubViewModelFactory(
        private val githubRepository: GithubRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GithubViewModel(githubRepository) as T
        }
    }
}