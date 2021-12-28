package com.chus.clua.lorempicsum.data.models

import com.google.gson.annotations.SerializedName

data class PictureRemoteModel(
    val id: Int?,
    val author: String?,
    val height: Int?,
    val width: Int?,
    val url: String?,
    @SerializedName("download_url")
    val downloadUrl: String?
)