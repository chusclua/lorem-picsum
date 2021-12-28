package com.chus.clua.lorempicsum.di


import com.chus.clua.lorempicsum.BuildConfig
import com.chus.clua.lorempicsum.data.network.LoremPicsumApi
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single {
        get<Retrofit>(
            parameters = { parametersOf(BuildConfig.BASE_URL) }
        ).create(LoremPicsumApi::class.java)
    }

}
