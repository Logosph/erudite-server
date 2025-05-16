package ru.logosph.auth

import kotlinx.serialization.Serializable


@Serializable
data class SignupSchema(
    val login: String,
    val email: String,
    val password: String
)

@Serializable
data class AuthSchema(
    val login: String,
    val password: String,
)



@Serializable
data class TokenSchema(
    val accessToken: String
)

