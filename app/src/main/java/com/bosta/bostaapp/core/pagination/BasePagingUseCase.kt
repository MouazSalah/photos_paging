package com.bosta.bostaapp.core.pagination

import androidx.paging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BasePagingUseCase<RequestItem : Any, ResultItem : Any, Params : Any> {

    abstract fun getPagingSource(params: Params?): PagingSource<Int, RequestItem>

    abstract fun mapper(responseItem: RequestItem): ResultItem

    fun invoke(scope: CoroutineScope, params: Params?, callBack: (PagingData<ResultItem>) -> Unit) {
        scope.launch(Dispatchers.IO) {
            execute(scope, params).collect {
                withContext(Dispatchers.Main) {
                    callBack.invoke(it)
                }
            }
        }
    }

    private fun execute(scope: CoroutineScope, params: Params?): Flow<PagingData<ResultItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { getPagingSource(params) }
        ).flow.map { pagingData ->
            pagingData.map {
                mapper(it)
            }
        }.cachedIn(scope)
    }

}