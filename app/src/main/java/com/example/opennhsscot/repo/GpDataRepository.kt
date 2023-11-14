package com.example.opennhsscot.repo

import com.example.opennhsscot.api.GpDataService
import com.example.opennhsscot.model.GpData
import javax.inject.Inject

class GpDataRepository @Inject constructor(
    val gpDataService: GpDataService) {

    suspend fun getGpData(): GpData {
        return gpDataService.getGpData()}

    suspend fun getGpDataSearch(practiceListSize: String): GpData {
        val sql  = "SELECT * FROM \"6e2b279f-5b9e-41ff-971c-1819c3df8ba9\" WHERE \"PracticeListSize\" > ${practiceListSize} ORDER BY \"PracticeListSize\" DESC"
        return gpDataService.getGpDataSearch(sql)
    }
}