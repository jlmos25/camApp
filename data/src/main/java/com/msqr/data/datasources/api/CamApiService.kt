package com.msqr.data.datasources.api

import com.msqr.data.datasources.api.model.Health
import retrofit2.Response
import retrofit2.http.GET

interface CamApiService {

    @GET("actuator/health")
    suspend fun isAlive(): Response<Health>
}