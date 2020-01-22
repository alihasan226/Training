package com.usl.usl.network.response.user

import com.google.gson.annotations.SerializedName

class UserResponse {
    var field: String = "defaultvalue"
        get() = field                     // getter
        set(value) { field = value }      // setter



}