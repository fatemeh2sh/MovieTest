package co.sharif.namava.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import co.sharif.namava.R
import co.sharif.namava.databinding.ActivityMainBinding
import co.sharif.namava.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.zip.Inflater

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                    as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun getViewModel(): MainViewModel {
       val viewModel : MainViewModel by viewModels()
       return viewModel
    }

    override fun getViewBinding(): ActivityMainBinding =
       ActivityMainBinding.inflate(layoutInflater)
}