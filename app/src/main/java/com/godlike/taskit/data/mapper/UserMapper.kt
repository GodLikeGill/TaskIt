package com.godlike.taskit.data.mapper

import com.godlike.taskit.data.source.local.entity.UserEntity
import com.godlike.taskit.data.source.network.dto.UserDto
import com.godlike.taskit.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        uid = uid,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatarUrl = avatarUrl,
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatarUrl = avatarUrl,
    )
}

fun UserDto.toDomain(): User {
    return User(
        uid = uid,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatarUrl = avatarUrl,
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        uid = uid,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatarUrl = avatarUrl,
    )
}