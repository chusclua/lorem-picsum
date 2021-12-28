package com.chus.clua.lorempicsum.di

import com.chus.clua.lorempicsum.data.mappers.PictureDomainModelMapper
import com.chus.clua.lorempicsum.data.repositories.PicturesRepositoryImp
import com.chus.clua.lorempicsum.domain.repositories.PicturesRepository
import org.koin.dsl.module


val repositoriesModule = module {

    single<PicturesRepository> { PicturesRepositoryImp(service = get(), mapper = PictureDomainModelMapper()) }

}