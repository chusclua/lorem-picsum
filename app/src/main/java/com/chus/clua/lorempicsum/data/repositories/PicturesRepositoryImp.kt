package com.chus.clua.lorempicsum.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.chus.clua.lorempicsum.data.mappers.PictureDomainModelMapper
import com.chus.clua.lorempicsum.data.network.PicturesService
import com.chus.clua.lorempicsum.data.paging.PicturesDataSource
import com.chus.clua.lorempicsum.domain.Either
import com.chus.clua.lorempicsum.domain.map
import com.chus.clua.lorempicsum.domain.models.PictureDomainModel
import com.chus.clua.lorempicsum.domain.repositories.PicturesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.lang.Exception


class PicturesRepositoryImp(
    private val service: PicturesService,
    private val mapper: PictureDomainModelMapper,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : PicturesRepository {

    override suspend fun fetchPictures(): Flow<PagingData<PictureDomainModel>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { PicturesDataSource(service) }
    ).flow.map { pagingData ->
        pagingData.map { mapper.mapFromRemote(it) }
    }

    override suspend fun fetchPicture(id: Int?): Either<Exception, PictureDomainModel> {
        return withContext(dispatcherIO) {
            service.fetchPictureInfo(id).map { mapper.mapFromRemote(it) }
        }
    }

}