package com.jicay.bookmanagement.domain.model

data class Book(val name: String, val author: String, var isreserved: Boolean = false)
