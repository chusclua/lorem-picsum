package com.chus.clua.lorempicsum.presentation.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chus.clua.lorempicsum.domain.fold
import com.chus.clua.lorempicsum.domain.mappers.PictureUiModelMapper
import com.chus.clua.lorempicsum.domain.models.PictureDomainModel
import com.chus.clua.lorempicsum.domain.repositories.PicturesRepository
import com.chus.clua.lorempicsum.presentation.models.PictureUiModel
import kotlinx.coroutines.launch

class PictureDetailViewModel(
    private val pictureID: Int,
    private val repository: PicturesRepository,
    private val mapper: PictureUiModelMapper
) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _pictureDetail = MutableLiveData<PictureUiModel>()
    val pictureDetail: LiveData<PictureUiModel> get() = _pictureDetail

    init {
        viewModelScope.launch {
            _isLoading.postValue(true)
            repository.fetchPicture(pictureID).fold(
                leftOp = { exception -> onError(exception) },
                rightOp = { model -> onSuccess(model) }
            )
        }
    }

    private fun onSuccess(model: PictureDomainModel) {
        _isLoading.postValue(false)
        _pictureDetail.postValue(mapper.mapFromDomain(model))
    }

    private fun onError(exception: Exception) {
        _isLoading.postValue(false)
        _errorMessage.postValue(exception.message)
    }
}