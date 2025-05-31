package com.ikseong.diexample.data.repository

import com.ikseong.diexample.data.dto.GithubUserResponseDto
import com.ikseong.diexample.data.service.GithubService

class GithubRepository(
    private val githubService: GithubService
) {
    fun getUserData(): Result<GithubUserResponseDto>{
        return runCatching {
            githubService.getUser()
        }
    }
}