package co.sharif.namava.ui.main.fragments.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.sharif.namava.databinding.FragmentPlayBinding


class PlayFragment : Fragment() {

    private  var mView :View ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (mView != null) return mView
        mView = FragmentPlayBinding.inflate(layoutInflater).root
        val w = container!!.width
        val h = container!!.height
        mView?.rotation = 90f
        mView?.translationX = ((w - h) / 2).toFloat()
        mView?.translationY = ((h - w) / 2).toFloat()
        val lp = mView?.layoutParams
        lp?.height = w
        lp?.width = h
        mView?.requestLayout()
        return mView
    }

}