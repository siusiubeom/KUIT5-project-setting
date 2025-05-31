package com.ikseong.diexample.data.service

import com.ikseong.diexample.data.dto.GithubUserResponseDto
import retrofit2.http.GET

interface GithubService {
    @GET("users/siusiubeom")
    fun getUser(): GithubUserResponseDto
}