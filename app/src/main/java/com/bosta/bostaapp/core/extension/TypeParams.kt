package com.bosta.bostaapp.core.extension

import com.google.gson.Gson
import org.json.JSONObject

interface HashMapParams {
    fun dataClass(): Any
}

fun HashMapParams?.toHashMapParams(): HashMap<String, String?>? {
    if (this == null) return null
    val params by lazy<HashMap<String, String?>> { HashMap() }
    try {
        JSONObject(Gson().toJson(dataClass())).let {
            if (it.names() != null)
                for (i in 0 until it.names()!!.length()) {
                    params[it.names()!!.getString(i)] =
                        it[it.names()!!.getString(i)].toString() + ""
                }
        }
    } catch (e: Exception) {
        print(e)
    }
    return if (params.isEmpty()) null else params
}
