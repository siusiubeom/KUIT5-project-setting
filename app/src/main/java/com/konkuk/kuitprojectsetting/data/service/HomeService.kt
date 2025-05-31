package com.konkuk.kuitprojectsetting.data.service

import com.konkuk.kuitprojectsetting.data.dto.response.BaseResponse
import com.konkuk.kuitprojectsetting.data.dto.response.ResponseHomeDto
import retrofit2.http.Body
import retrofit2.http.GET

interface HomeService {
    @GET("home")
    suspend fun getHomeData(
        @Body content: String
    ): BaseResponse<ResponseHomeDto>
}