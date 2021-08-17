package co.sharif.namava.ui.main.fragments.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import co.sharif.namava.R
import co.sharif.namava.databinding.FragmentDetailBinding
import co.sharif.namava.databinding.FragmentSearchBinding
import co.sharif.namava.ui.base.BaseFragment
import co.sharif.namava.ui.main.fragments.search.SearchViewModel
import co.sharif.namava.ui.play.PlayActivity
import co.sharif.namava.utils.getTimestamp
import co.sharif.namava.utils.loadImage
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>(),View.OnClickListener {

    private val argDetail : DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        setFill()
        setClick()
    }

    private fun setFill(){
        argDetail?.let {
            mViewBindingFrag.tvTitle.text = it.argVideo.name
            mViewBindingFrag.tvDescription.text = it.argVideo.description
            mViewBindingFrag.tvFavorite.text = it.argVideo.metadata?.connections?.likes?.total.toString()
            mViewBindingFrag.tvComment.text = it.argVideo.metadata?.connections?.comments?.total.toString()
            mViewBindingFrag.tvPlay.text = it.argVideo.metadata?.connections?.albums?.total.toString()
            mViewBindingFrag.imgMovie.loadImage(it.argVideo?.pictures?.sizes?.get(4)?.link)
            it.argVideo.duration?.let {
                mViewBindingFrag.tvTime.text = getTimestamp(it)
            }
        }
    }

    private fun setClick(){
        mViewBindingFrag.imgMovie.setOnClickListener(this)
    }

    //region override
    override fun getViewModel(): DetailViewModel {
        val viewModel : DetailViewModel by viewModels()
        return viewModel
    }

    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun onClick(v: View?) {
        when(v){
            mViewBindingFrag.imgMovie -> {
                val intent = Intent(activity, PlayActivity::class.java)
                    .apply {
                        this.putExtra("uri",argDetail.argVideo.link)
                        startActivity(this)
                    }
              //  navController.navigate(R.id.action_detailFragment_to_playFragment)
            }
        }
    }
    //endregion
}