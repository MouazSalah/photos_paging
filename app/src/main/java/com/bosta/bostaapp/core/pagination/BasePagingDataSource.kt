package com.bosta.bostaapp.core.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.core.usecase.BaseResponse
import com.techlife.winwin.core.base.pagination.BasePagingResponse
import retrofit2.HttpException
import java.io.IOException

const val INITIAL_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 20

abstract class BasePagingDataSource<ResponseItem : Any> :
    PagingSource<Int, ResponseItem>() {

    abstract suspend fun execute(): NetworkResponse<BaseResponse<BasePagingResponse<ResponseItem>>, ErrorResponse>
    abstract val queryParams: PagingParams

    open fun onResponseReceived(data: BasePagingResponse<ResponseItem>?) {}

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseItem> {
        val position = params.key?.let {
            it * NETWORK_PAGE_SIZE / params.loadSize
        } ?: INITIAL_PAGE_INDEX
        return try {
            queryParams.page = position
            when (val response = execute()) {
                is NetworkResponse.Success -> {
                    onResponseReceived(response.body.data)
                    val nextKey =
                        if (response.body.data?.listOfData?.isEmpty() == true) {
                            null
                        } else {
                            // initial load size = 3 * NETWORK_PAGE_SIZE
                            // ensure we're not requesting duplicating items, at the 2nd request
                            position.plus(1)
                        }
                    val prevKey = if (position == INITIAL_PAGE_INDEX) {
                        null
                    } else {
                        (position * params.loadSize / NETWORK_PAGE_SIZE) - 1
                    }

                    val listOfData = response.body.data?.listOfData?.filterNotNull() ?: emptyList()
                    LoadResult.Page(
                        data = listOfData,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                is NetworkResponse.NetworkError -> {
                    LoadResult.Error(response.error)
                }
                is NetworkResponse.ServerError -> {
                    LoadResult.Error(Throwable(response.body?.message))
                }
                is NetworkResponse.UnknownError -> {
                    response.error.message
                    LoadResult.Error(response.error)
                }
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, ResponseItem>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}