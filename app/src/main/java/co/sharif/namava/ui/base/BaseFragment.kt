package co.sharif.namava.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {

    private lateinit var currentView: View
    lateinit var mViewBindingFrag: VB
    lateinit var navController: NavController
    protected val mViewModelFrag by lazy { getViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setupViewBinding(getViewBinding())
        return currentView
    }

    private fun setupViewBinding(viewBinding: VB) {
        mViewBindingFrag = viewBinding
        currentView = viewBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = findNavController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewLifecycleOwnerLiveData.removeObservers(this)
    }

    abstract fun getViewModel(): VM

    abstract fun getViewBinding(): VB
}