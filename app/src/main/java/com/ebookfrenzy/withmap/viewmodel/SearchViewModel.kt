package com.ebookfrenzy.withmap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ebookfrenzy.withmap.data.SearchLocationResult
import com.ebookfrenzy.withmap.network.KakaoService
import com.ebookfrenzy.withmap.network.response.KeywordAddressDocument
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created By Yun Hyeok
 * on 9월 13, 2019
 */

class SearchViewModel(private val kakaoService: KakaoService) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _searchResult = MutableLiveData<List<KeywordAddressDocument>>()
    val searchResult: LiveData<List<KeywordAddressDocument>>
        get() = _searchResult

    private val _selectedLocation = MutableLiveData<SearchLocationResult>()
    val selectedLocation: LiveData<SearchLocationResult>
        get() = _selectedLocation

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun getSearchResult(query: String) {
        _isLoading.value = true
        addDisposable(
            kakaoService.requestKakaoKeywordSearch(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _searchResult.value = it.documents
                    _isLoading.value = false
                }, { /* 실패시 코드 작성 */ }
                )
        )
    }

    fun setSelectedLocation(location: SearchLocationResult) {
        _selectedLocation.value = location
    }
}