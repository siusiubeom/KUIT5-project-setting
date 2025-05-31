package com.ikseong.diexample.ui.github.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ikseong.diexample.data.repository.GithubRepository
import com.ikseong.diexample.mapper.toUiState
import com.ikseong.diexample.ui.github.unstate.GithubUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GithubViewModel(
    private val githubRepository: GithubRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(GithubUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchUserData()
    }
    private fun fetchUserData(){
        githubRepository.getUserData().fold(
            onSuccess = { data ->
                _uiState.value = data.toUiState()
            },
            onFailure = {error ->
                Log.e("okhttpError", error.message.toString())
            }
        )
    }
}