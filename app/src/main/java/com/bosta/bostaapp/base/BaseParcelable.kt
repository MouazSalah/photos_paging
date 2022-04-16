package com.bosta.bostaapp.base

import android.os.Parcelable

interface BaseParcelable : Parcelable {
    fun unique(): Any
}