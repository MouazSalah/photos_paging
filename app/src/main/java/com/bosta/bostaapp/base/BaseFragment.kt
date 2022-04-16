package com.bosta.bostaapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bosta.bostaapp.core.extension.castToActivity
import com.bosta.bostaapp.core.extension.showAppToast
import com.bosta.bostaapp.features.activity.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
    open fun mPageTitle(): String? = null
    open fun mShowBackBtn(): Boolean = false

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        setToolbarTitle(mPageTitle())
        showBackButton(mShowBackBtn())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setToolbarTitle(title: String?) {
        castToActivity<MainActivity> {
            it?.setToolbarTitle(title)
        }
    }

    private fun showBackButton(show: Boolean) {
        castToActivity<MainActivity> {
            it?.showBackBtn(show)
        }
    }

    fun showProgress(show: Boolean = false) {
        castToActivity<MainActivity> {
            it?.showProgress(show)
        }
    }

    fun closeFragment() {
        activity?.onBackPressed()
    }

    fun showToast(message: String?) {
        context?.showAppToast(message)
    }
}
