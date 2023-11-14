package com.example.opennhsscot.model

data class Record(
    val AddressLine1: String,
    val AddressLine2: String,
    val AddressLine3: String,
    val AddressLine4: String,
    val DataZone: String,
    val Dispensing: String,
    val GPCluster: String,
    val GPPracticeName: String,
    val HB: String,
    val HSCP: String,
    val Postcode: String,
    val PracticeCode: String,
    val PracticeListSize: String,
    val PracticeType: String,
    val TelephoneNumber: String,
    val _full_text: String,
    val _id: Int
)