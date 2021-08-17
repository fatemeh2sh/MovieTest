package co.sharif.namava.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import co.sharif.namava.utils.setColorStatusBar
import java.util.*

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> :
    AppCompatActivity() {

    lateinit var mViewBinding: VB
    lateinit var currentView: View

    private val mViewModel by lazy { getViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setupViewBinding(getViewBinding())

        setColorStatusBar(this)
    }

    private  fun setupViewBinding(binding: VB) {
        mViewBinding = binding
        currentView = binding!!.root
        setContentView(currentView)
    }

    abstract fun getViewModel(): VM

    abstract fun getViewBinding(): VB
}