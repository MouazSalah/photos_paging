package com.bosta.bostaapp.core.usecase

import com.bosta.bostaapp.core.network.Resource
import com.bosta.bostaapp.core.response.NetworkResponse
import kotlinx.coroutines.*

/**
- Acts as a contract for all the use cases in our application:
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.

 * @param RequestType: a return type which is the result of the *Network Execution*.
 * @param Params: a parameters class which will be consumed inside the [invoke] function
in case we need extra data for our use case.
 * @param ResultType: the result returned after mapping the response to to the *View*
 */
abstract class BaseUseCase<RequestType : Any, ResultType : Any, in Params> :
    BaseCommonUseCase<RequestType, ResultType, Params>() {
    fun invoke(
        scope: CoroutineScope,
        params: Params? = null,
        onResult: (Resource<ResultType>) -> Unit = {},
    ) {
        scope.launch(handler(onResult) + Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            runFlow(executeRemote(params), onResult).collect {
                when (it) {
                    is NetworkResponse.Success -> {
                        val res = mapper(it.body)
                        onResult.invoke(Resource.success(res))
                    }
                    is NetworkResponse.NetworkError -> onResult.invoke(Resource.failure(it.error.toString()))
                    is NetworkResponse.ServerError -> onResult.invoke(Resource.failure(it.body?.message))
                    is NetworkResponse.UnknownError -> onResult.invoke(Resource.failure(it.error.toString()))
                }
                onResult.invoke(Resource.loading(false))
            }
        }
    }
}