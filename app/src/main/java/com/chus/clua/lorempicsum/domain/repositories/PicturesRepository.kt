package com.chus.clua.lorempicsum.domain.repositories

import androidx.paging.PagingData
import com.chus.clua.lorempicsum.domain.Either
import com.chus.clua.lorempicsum.domain.models.PictureDomainModel
import kotlinx.coroutines.flow.Flow
import java.lang.Exception


interface PicturesRepository {
    suspend fun fetchPictures(): Flow<PagingData<PictureDomainModel>>
    suspend fun fetchPicture(id: Int?): Either<Exception, PictureDomainModel>
}