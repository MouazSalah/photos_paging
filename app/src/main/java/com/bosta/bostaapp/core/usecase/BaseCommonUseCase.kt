package com.bosta.bostaapp.core.usecase

import com.bosta.bostaapp.core.network.Resource
import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class BaseCommonUseCase<RequestType : Any, ResultType : Any, in Params> {
    //map class from response to the result needed in View
    abstract fun mapper(req: RequestType): ResultType

    //run the remote api
    abstract fun executeRemote(params: Params?): Flow<NetworkResponse<RequestType, ErrorResponse>>

    fun handler(onResult: (Resource<ResultType>) -> Unit) =
        CoroutineExceptionHandler { _, exception ->
            onResult.invoke(Resource.loading(false))
            showFailureMessage(onResult, exception.message ?: exception.toString())
        }

    fun <T> runFlow(resFlow: Flow<T>, onResult: (Resource<ResultType>) -> Unit): Flow<T> {
        return resFlow.catch { e ->
            showFailureMessage(onResult, e.message ?: e.toString())
        }.flowOn(Dispatchers.IO)
    }

    private fun showFailureMessage(onResult: (Resource<ResultType>) -> Unit, message: String?) {
        onResult.invoke(Resource.loading(false))
        onResult.invoke(Resource.failure(message))
    }
}