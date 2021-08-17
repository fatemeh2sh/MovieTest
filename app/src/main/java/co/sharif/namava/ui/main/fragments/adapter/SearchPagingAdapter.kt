package co.sharif.namava.ui.main.fragments.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.sharif.namava.data.model.Datum
import co.sharif.namava.databinding.ItemMovieBinding
import co.sharif.namava.interfaces.ItemListener
import co.sharif.namava.utils.getTimestamp
import co.sharif.namava.utils.loadImage

class SearchPagingAdapter( context: Context)
    : PagingDataAdapter<Datum, SearchPagingAdapter.VideoHolder>(DataDiff) {

    private var mContext:Context = context
    private lateinit var mListener: ItemListener

    fun setListener(listener: ItemListener) {
        mListener = listener
    }

    inner class VideoHolder(var view:ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Datum) {
            view.tvTitle.text = item.name
            view.tvCaption.text = item.description
            view.imgThamnail.loadImage(item?.pictures?.sizes?.get(1)?.link)
            item.duration?.let {
                view.tvTime.text = getTimestamp(it)
            }
            view.constItemVideo.setOnClickListener {
                mListener.onClicked(item)
            }
        }
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
          holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder{
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return VideoHolder(binding)
    }

    object DataDiff : DiffUtil.ItemCallback<Datum>() {
        override fun areItemsTheSame(oldItem: Datum, newItem: Datum): Boolean {
            return   oldItem.createdTime == newItem.createdTime
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Datum, newItem: Datum): Boolean {
            return oldItem == newItem
        }
    }
}