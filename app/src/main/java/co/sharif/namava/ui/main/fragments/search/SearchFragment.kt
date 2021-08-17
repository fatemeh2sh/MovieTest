package co.sharif.namava.ui.main.fragments.search

import android.accounts.NetworkErrorException
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import co.sharif.namava.R
import co.sharif.namava.data.model.Datum
import co.sharif.namava.databinding.FragmentSearchBinding
import co.sharif.namava.interfaces.ItemListener
import co.sharif.namava.ui.base.BaseFragment
import co.sharif.namava.ui.main.fragments.adapter.SearchPagingAdapter
import co.sharif.namava.utils.hide
import co.sharif.namava.utils.networkHelper.ResultNet
import co.sharif.namava.utils.show
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel,FragmentSearchBinding>(),
    ItemListener {

    //region variable
    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    private var loading: Boolean = false
        set(value) {
            showLoading(value)
            field = value
        }

    private var loadingPaging: Boolean = false
        set(value) {
           showLoadingPaging(value)
            field = value
        }

    //endregion

    lateinit var searchPagingAdapter :SearchPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init(){
        setupRecyclerView()
        bindingRxView()
        setupObserverVideoPaging()
    }


    //region observer
    private fun setupObserverVideoPaging() {
        searchPagingAdapter = SearchPagingAdapter(this.requireContext())
        searchPagingAdapter.setListener(this)
        mViewBindingFrag?.rvMovie?.adapter = searchPagingAdapter
        searchPagingAdapter.addLoadStateListener { loadState ->
            when {
                loadState.refresh is LoadState.Error -> {
                    if ((loadState.refresh as LoadState.Error).error is NetworkErrorException)
                        showToast("Error")

                    loading = false
                    loadingPaging = false
                }
                loadState.append is LoadState.Error -> {
                    if ((loadState.append as LoadState.Error).error is NetworkErrorException)
                        showToast("Error")

                    loading = false
                    loadingPaging = false
                }
                loadState.refresh == LoadState.Loading -> {
                    loading = true
                    loadingPaging = false
                }
                loadState.append == LoadState.Loading -> {
                    loading = false
                    loadingPaging = true
                }
                loadState.refresh is LoadState.NotLoading && loadState.append is LoadState.NotLoading -> {
                    if(searchPagingAdapter.itemCount == 0 && mViewModelFrag.search.isNotEmpty())
                        manageEmptyList(true)
                    loading = false
                    loadingPaging = false
                }
            }
        }
        mViewModelFrag.dataProductPaging.observe(viewLifecycleOwner, {
            manageSuccessResponsePaging(it)
        })
    }

    private fun bindingRxView() {
        var disposable = mViewBindingFrag.tvSearch.textChanges()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if( mViewModelFrag.search.isNotEmpty() || it.isNotEmpty()) {
                        mViewModelFrag.search = it.toString()
                        manageEmptyList(false)
                        searchPagingAdapter.refresh()
                    }else {
                        manageEmptyList(true)
                    }
                }

        compositeDisposable.add(disposable)
    }

    //endregion

    //region manage
    private fun manageSuccessResponsePaging(pagingData: PagingData<Datum>) {
        try {
            searchPagingAdapter.submitData(lifecycle, pagingData)
        } catch (e: Exception) {
        }
    }

    private fun manageEmptyList(check:Boolean) {
        when(check){
            true -> {
                mViewBindingFrag?.inError?.root?.show()
                mViewBindingFrag?.rvMovie?.hide()}
            false -> {
                mViewBindingFrag?.inError?.root?.hide()
                mViewBindingFrag?.rvMovie?.show()
            }
        }
    }

    //endregion

    private fun setupRecyclerView() {
        mViewBindingFrag?.rvMovie?.layoutManager = LinearLayoutManager(this.requireContext())
    }

    private fun showToast(message:String){
        Toast.makeText(this.requireContext(),message,Toast.LENGTH_SHORT).show()
    }


    //region loading
    private fun showLoading(value:Boolean) {
        when(value){
            true -> mViewBindingFrag?.progress?.show()
            false -> mViewBindingFrag?.progress?.hide()
        }
    }

    private fun showLoadingPaging(value: Boolean) {
        when(value){
            true -> mViewBindingFrag?.progressPaging?.show()
            false -> mViewBindingFrag?.progressPaging?.hide()
        }
    }

    //endregion

    //region override
    override fun getViewModel(): SearchViewModel {
        val viewModel : SearchViewModel by viewModels()
        return viewModel
    }

    override fun getViewBinding(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)


    override fun onClicked(item: Datum) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(
            argVideo = item
        )
        navController.navigate(action)

    }

    //endregion
}