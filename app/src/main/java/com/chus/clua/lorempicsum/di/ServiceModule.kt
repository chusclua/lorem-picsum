package com.chus.clua.lorempicsum.di

import com.chus.clua.lorempicsum.data.network.PicturesService
import org.koin.dsl.module

val serviceModule = module {

    single { PicturesService(api = get()) }

}