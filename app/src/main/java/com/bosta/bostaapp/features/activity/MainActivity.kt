package com.bosta.bostaapp.features.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.bosta.bostaapp.R
import com.bosta.bostaapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private var navController: NavController? = null
    private val mBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        navController?.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        if (destination.id == R.id.photoViewFragment) {
            mBinding.clToolbar.visibility = View.GONE
        } else {
            mBinding.clToolbar.visibility = View.VISIBLE
        }
    }

    fun setToolbarTitle(title: String?) {
        mBinding.tvTitle.text = title
    }

    fun showBackBtn(show: Boolean?) {
        if (show == true) {
            mBinding.btnBack.visibility = View.VISIBLE
        } else {
            mBinding.btnBack.visibility = View.INVISIBLE
        }
    }

    fun showProgress(show: Boolean?) {
        if (show == true)
            mBinding.progressBar.visibility = View.VISIBLE
        else
            mBinding.progressBar.visibility = View.GONE
    }
}

