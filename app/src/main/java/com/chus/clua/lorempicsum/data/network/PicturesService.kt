package com.chus.clua.lorempicsum.data.network

import com.chus.clua.lorempicsum.data.utils.serviceHandler

class PicturesService(private val api: LoremPicsumApi) {
    suspend fun fetchPicturesList(page: Int?) = api.fetchPicturesList(page)
    suspend fun fetchPictureInfo(id: Int?) =
        serviceHandler {
            api.fetchPictureInfo(id)
        }
}