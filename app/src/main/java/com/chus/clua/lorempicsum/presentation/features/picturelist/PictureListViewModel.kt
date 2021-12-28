package com.chus.clua.lorempicsum.presentation.features.picturelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.chus.clua.lorempicsum.domain.mappers.PictureUiModelMapper
import com.chus.clua.lorempicsum.domain.repositories.PicturesRepository
import com.chus.clua.lorempicsum.presentation.models.PictureUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class PictureListViewModel(
    picturesRepository: PicturesRepository,
    private val mapper: PictureUiModelMapper
) : ViewModel() {

    private lateinit var _picturesFlow: Flow<PagingData<PictureUiModel>>
    val picturesFlow: Flow<PagingData<PictureUiModel>> get() = _picturesFlow

    init {
        viewModelScope.launch {
            _picturesFlow = picturesRepository.fetchPictures().cachedIn(viewModelScope).map { pagingData ->
                pagingData.map { mapper.mapFromDomain(it) }
            }
        }
    }

}