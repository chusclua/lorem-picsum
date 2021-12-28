package com.chus.clua.lorempicsum.di

import com.chus.clua.lorempicsum.presentation.features.picturelist.PictureListViewModel
import com.chus.clua.lorempicsum.domain.mappers.PictureUiModelMapper
import com.chus.clua.lorempicsum.presentation.features.detail.PictureDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        PictureListViewModel(
            picturesRepository = get(),
            mapper = PictureUiModelMapper()
        )
    }

    viewModel { (id: Int) ->
        PictureDetailViewModel(
            id,
            repository = get(),
            mapper = PictureUiModelMapper()
        )
    }

}