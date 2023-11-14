package com.example.opennhsscot.api

import com.example.opennhsscot.model.GpData
import com.example.opennhsscot.utils.URL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GpDataService {

    // Load list of all surgeries
    @GET(URL)
    suspend fun getGpData(): GpData

    // Search Query
    @GET("datastore_search_sql")
    suspend fun getGpDataSearch(
                @Query("sql") sql: String
    ): GpData
}