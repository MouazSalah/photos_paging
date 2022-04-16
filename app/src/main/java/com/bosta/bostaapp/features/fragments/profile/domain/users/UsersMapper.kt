package com.bosta.bostaapp.features.fragments.profile.domain.users

import com.bosta.bostaapp.core.mapper.BaseListMapper
import com.bosta.bostaapp.features.fragments.profile.data.users.UserDetails
import com.bosta.bostaapp.features.fragments.profile.data.users.UsersResponseItem

object UsersMapper : BaseListMapper<List<UsersResponseItem>, UsersResponseItem, UserDetails> {
    override fun mapListData(res: List<UsersResponseItem>): List<UserDetails> =
        res.map { mapItem(it) }

    override fun mapItem(res: UsersResponseItem?): UserDetails =
        UserDetails(
            userId = res?.id ?: -1,
            name = res?.name ?: "",
            address = "${res?.address?.street} ${res?.address?.suite} ${res?.address?.city} ${res?.address?.zipcode}",
        )
}