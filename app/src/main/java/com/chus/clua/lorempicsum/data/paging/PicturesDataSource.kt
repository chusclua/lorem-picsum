package com.chus.clua.lorempicsum.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chus.clua.lorempicsum.data.models.PictureRemoteModel
import com.chus.clua.lorempicsum.data.network.PicturesService

class PicturesDataSource(
    private val picturesService: PicturesService
) : PagingSource<Int, PictureRemoteModel>() {

    override fun getRefreshKey(state: PagingState<Int, PictureRemoteModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PictureRemoteModel> {
        return try {
            val pageNumber = params.key ?: 1
            val data = picturesService.fetchPicturesList(pageNumber).body().orEmpty()
            val nextPageNumber = if (data.isEmpty()) null else pageNumber + 1
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}