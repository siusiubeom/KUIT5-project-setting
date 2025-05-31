package com.konkuk.kuitprojectsetting.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuitprojectsetting.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {
    fun getHomeData(
        request: String
    ){
        viewModelScope.launch {
            homeRepository.getHomeData(request)
                .onSuccess {  }
                .onFailure {  }
        }
    }
}