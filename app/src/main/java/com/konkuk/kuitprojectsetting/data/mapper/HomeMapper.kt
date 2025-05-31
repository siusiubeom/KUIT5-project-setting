package com.konkuk.kuitprojectsetting.data.mapper

import com.konkuk.kuitprojectsetting.data.dto.response.ResponseHomeDto
import com.konkuk.kuitprojectsetting.domain.entity.HomeModel

fun ResponseHomeDto.toHomeModel() = HomeModel(
    content = content,
    id = id
)