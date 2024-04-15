package com.rahul.githubapp.features.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.githubapp.features.model.ResponseDataItem
import com.rahul.githubapp.repository.GitRepository
import kotlinx.coroutines.launch

class GitViewModel : ViewModel() {

    val totalList = MutableLiveData<ArrayList<ResponseDataItem>>()
    private val _uiList = MutableLiveData<ArrayList<ResponseDataItem>>()
    val dataList: LiveData<ArrayList<ResponseDataItem>> = _uiList
    private var currentPage = 0
    private val itemsPerPage = 10
    private var totalItems = 0
    private val repository = GitRepository()

    fun getData() {
        viewModelScope.launch {
            val response = repository.getList()
            response.body().let {
                totalList.postValue(it)

            }
        }
    }

    private fun loadData() {
        totalItems = totalList.value?.size ?: 0
        val startIndex = (currentPage - 1) * itemsPerPage
        val endIndex = startIndex + itemsPerPage
        val newData = arrayListOf<ResponseDataItem>()
        if (endIndex <= totalItems) {
            for (i in startIndex until endIndex) {
                totalList.value?.get(i)?.let { newData.add(it) }
            }
        }

        _uiList.value = newData
    }

    fun loadMoreData() {
        currentPage++
        loadData()
    }
}