package ru.logosph

import ru.logosph.auth.SignupSchema

object MemoryCache {
    val users: MutableList<SignupSchema> = mutableListOf()
    val tokensToUsers: MutableMap<String, SignupSchema> = mutableMapOf()
    val usersToTokens: MutableMap<String, String> = mutableMapOf()
}