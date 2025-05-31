package com.konkuk.kuitprojectsetting.domain.repository

import com.konkuk.kuitprojectsetting.domain.entity.HomeModel

interface HomeRepository {
    suspend fun getHomeData(request: String): Result<HomeModel>
}