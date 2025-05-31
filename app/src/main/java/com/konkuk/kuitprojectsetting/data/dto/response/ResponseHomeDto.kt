package com.konkuk.kuitprojectsetting.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    val id: String,
    val content: String
)
