package com.msqr.data.di

import com.msqr.data.datasources.api.CamApiService
import com.msqr.data.datasources.remote.utils.BaseUrlInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideInterceptor(): BaseUrlInterceptor {
    return BaseUrlInterceptor("http://localhost:8080/")
}
fun provideHttpClient(urlInterceptor: BaseUrlInterceptor): OkHttpClient {
    return OkHttpClient
        .Builder()
        .readTimeout(1, TimeUnit.SECONDS)
        .connectTimeout(1, TimeUnit.SECONDS)
        .build()
}


fun provideConverterFactory(): GsonConverterFactory =
    GsonConverterFactory.create()


fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://192.168.1.55:8080/")
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideService(retrofit: Retrofit): CamApiService =
    retrofit.create(CamApiService::class.java)


val networkModule= module {
    single { provideInterceptor()}
    single { provideHttpClient(get()) }
    single { provideConverterFactory() }
    single { provideRetrofit(get(),get()) }
    single { provideService(get()) }
}