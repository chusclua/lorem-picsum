package com.chus.clua.lorempicsum.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val retrofitModule = module {

    factory { (baseUrl: String) ->
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get(named(DEFAULT_OKHTTP_CLIENT)))
            .build()
    }

    single(named(DEFAULT_OKHTTP_CLIENT)) {
        OkHttpClient().newBuilder()
            .connectTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

}

private const val DEFAULT_OKHTTP_CLIENT = "defaultOkhttpClient"
private const val READ_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 60L