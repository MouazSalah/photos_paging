package com.bosta.bostaapp.core.pagination

import com.bosta.bostaapp.core.extension.HashMapParams

abstract class PagingParams : HashMapParams {
    abstract var page: Int
}