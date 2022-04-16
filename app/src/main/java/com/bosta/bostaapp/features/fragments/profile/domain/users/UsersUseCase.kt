package com.bosta.bostaapp.features.fragments.profile.domain.users

import android.util.Log
import com.bosta.bostaapp.core.network.MainRepository
import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.core.usecase.BaseUseCase
import com.bosta.bostaapp.features.fragments.profile.data.users.UserDetails
import com.bosta.bostaapp.features.fragments.profile.data.users.UsersResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersUseCase @Inject constructor(private val mainRepo: MainRepository) :
    BaseUseCase<List<UsersResponseItem>, List<UserDetails>, Any?>() {
    override fun mapper(req: List<UsersResponseItem>): List<UserDetails> =
        UsersMapper.mapListData(req)

    override fun executeRemote(params: Any?): Flow<NetworkResponse<List<UsersResponseItem>, ErrorResponse>> =
        flow {
            Log.e("Bostaaa ", mainRepo.getAllUsers().toString())
            emit(mainRepo.getAllUsers())
        }
}