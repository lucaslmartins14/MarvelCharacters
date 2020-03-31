package com.example.marvelcharacters

abstract class GenericCallback<T> {

    var success: Boolean = false
    var `object`: T? = null
    var innerCallback: GenericCallback<*>? = null
    abstract fun call(result: String)
}