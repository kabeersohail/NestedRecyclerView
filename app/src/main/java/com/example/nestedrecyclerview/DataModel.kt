package com.example.nestedrecyclerview

data class DataModel(
    val nestedList: List<String>,
    val itemText: String,
    var isExpandable: Boolean = false
)