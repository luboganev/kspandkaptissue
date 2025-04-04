package dev.luboganev.kspnumberofloadedfilesissue.remotetime

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteTimeApiService {

    @GET("timezone/Europe%2FLondon")
    suspend fun getLondonTime(): RemoteTimeResponse
}
