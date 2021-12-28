package com.chus.clua.lorempicsum.data.network

import com.chus.clua.lorempicsum.data.models.PictureRemoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoremPicsumApi {
    @GET(LIST)
    suspend fun fetchPicturesList(
        @Query(PAGE_PARAM) page: Int?,
        @Query(LIMIT_PARAM) limit: Int? = LIMIT_VALUE
    ): Response<List<PictureRemoteModel>>

    @GET(INFO)
    suspend fun fetchPictureInfo(@Path(ID_PATH) id: Int?): Response<PictureRemoteModel>
}

private const val LIST = "v2/list"
private const val INFO = "id/{id}/info"

private const val ID_PATH = "id"
private const val PAGE_PARAM = "page"
private const val LIMIT_PARAM = "limit"

private const val LIMIT_VALUE = 20