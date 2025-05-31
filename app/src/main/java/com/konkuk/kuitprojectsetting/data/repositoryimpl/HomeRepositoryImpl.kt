package com.konkuk.kuitprojectsetting.data.repositoryimpl

import com.konkuk.kuitprojectsetting.data.mapper.toHomeModel
import com.konkuk.kuitprojectsetting.data.service.HomeService
import com.konkuk.kuitprojectsetting.domain.entity.HomeModel
import com.konkuk.kuitprojectsetting.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
): HomeRepository {
    override suspend fun getHomeData(request: String): Result<HomeModel> =
        kotlin.runCatching {
            val response = homeService.getHomeData(request)
            response.data.toHomeModel()
        }
}