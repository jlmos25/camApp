package com.msqr.data.datasources.remote.utils

import okhttp3.Interceptor
import okhttp3.Response

class BaseUrlInterceptor(private var baseUrl: String) : Interceptor {
    fun updateBaseUrl(newUrl: String) {
        baseUrl = newUrl
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url().newBuilder()
            .host(baseUrl)  // update the host (www.example.com)
            .build()
        val newRequest = originalRequest.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}