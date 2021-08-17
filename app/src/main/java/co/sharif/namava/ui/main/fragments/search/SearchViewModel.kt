package co.sharif.namava.ui.main.fragments.search

import android.accounts.NetworkErrorException
import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import co.sharif.namava.data.model.Datum
import co.sharif.namava.repository.SearchRepository
import co.sharif.namava.ui.base.BaseViewModel
import co.sharif.namava.utils.networkHelper.ConnectionNet
import dagger.hilt.android.qualifiers.ActivityContext

class SearchViewModel @ViewModelInject constructor(
    @ActivityContext private val context: Context,
     val searchRepository: SearchRepository,
     val connectionNet: ConnectionNet) : BaseViewModel() {

    val dataProductPaging = Pager(PagingConfig(pageSize = 10)) {
        BaseDataSourcePagination()
    }.liveData.cachedIn(viewModelScope)


    var search:String = ""
    var receiveData = false

   inner class BaseDataSourcePagination() : PagingSource<Int, Datum>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Datum> {
            try {
                if (!connectionNet.isNetworkConnected())
                    return LoadResult.Error(NetworkErrorException())
                val currentLoadingPageKey = params.key ?: 1
                val responseData = mutableListOf<Datum>()
                var total = 0
                var per_page = 0
                var count = 0

                receiveData = false

                if(search.isEmpty()) {
                    receiveData = true
                    return LoadResult.Page(data = responseData, prevKey = null, nextKey = null)
                }

                searchRepository.getVideo(search)?.let {
                    total = it.body()?.total!!
                    per_page = it.body()?.per_page!!
                    count = total / per_page + 1

                    if (currentLoadingPageKey <= count) {
                        it.body()?.let { it1 ->
                            responseData.addAll(it1.resultData)
                        }
                    }
                }
                receiveData = true

                val nextKey =
                    if(currentLoadingPageKey == count) null
                    else currentLoadingPageKey.plus(1)

                val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
               return LoadResult.Page(
                    data = responseData,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } catch (e: Exception) {
               return LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Datum>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }
    }
}