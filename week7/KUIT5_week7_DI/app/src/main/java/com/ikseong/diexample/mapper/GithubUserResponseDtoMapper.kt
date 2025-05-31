package com.ikseong.diexample.mapper

import com.ikseong.diexample.data.dto.GithubUserResponseDto
import com.ikseong.diexample.ui.github.unstate.GithubUiState

fun GithubUserResponseDto.toUiState() = GithubUiState (
    name = this.login,
    id = this.id.toLong(),
    githubUrl = this.url
)