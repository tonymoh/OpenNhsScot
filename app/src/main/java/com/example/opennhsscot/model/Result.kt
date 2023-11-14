package com.example.opennhsscot.model

data class Result(
    val fields: List<Field>,
    val records: List<Record>,
    val sql: String
)