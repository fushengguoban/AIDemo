package com.jthl.aidemo.feature.mine

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val id: String,
    val name: String,
    val avatar: String? = null,
    val email: String,
    val bio: String? = null
)
