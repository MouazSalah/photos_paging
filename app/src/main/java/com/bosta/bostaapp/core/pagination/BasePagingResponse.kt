package com.techlife.winwin.core.base.pagination

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.bosta.bostaapp.core.usecase.BaseCommonResponse
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class BasePagingResponse<T>(
    @SerializedName("another_account")
    val account: ClientChatResponse? = null,

    @SerializedName("data")
    val listOfData: @RawValue List<T?>? = null,

    @SerializedName("subscriptions")
    val isSubscriptions: Boolean? = null,

    @SerializedName("check_subscriptions")
    val checkSubscriptions: Boolean? = null,

    @SerializedName("subscription_date")
    val subscriptionDate: String? = null,

    @SerializedName("subscription_expiry_date")
    val subscriptionExpiryDate: String? = null,

    @field:SerializedName("status_subscriptions")
    val statusSubscriptions: Int? = null,

    @field:SerializedName("users_count")
    val usersCount: Int? = null,

    @field:SerializedName("price")
    val price: Int? = null,
    @field:SerializedName("remaining_time")
    val remainingTime: Long? = null,
) : BaseCommonResponse()

@Parcelize
data class ClientChatResponse(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null
) : Parcelable

@Parcelize
data class MySubscriptionDetails(
    val checkSubscriptions: Boolean,
    val statusSubscription: Int,
    val subscriptionDate: String,
    val subscriptionExpiryDate: String
) : Parcelable

