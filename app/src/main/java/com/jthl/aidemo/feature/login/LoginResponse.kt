package com.jthl.aidemo.feature.login

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val token: String,
    val message: String? = null,
    val userId: String? = null,
    val error: String? = null
)
