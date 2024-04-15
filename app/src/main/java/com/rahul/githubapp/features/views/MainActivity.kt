package com.rahul.githubapp.features.views

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.githubapp.R
import com.rahul.githubapp.databinding.ActivityMainBinding
import com.rahul.githubapp.features.model.ResponseDataItem
import com.rahul.githubapp.features.viewModel.GitViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GitViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var listAdapter: ListAdapter
    private lateinit var rvLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[GitViewModel::class.java]
        rvLayoutManager = LinearLayoutManager(this)
        setObserver()
        getData()
        setRv()

    }

    private fun setRv() {
        listAdapter = ListAdapter(arrayListOf(), ::onTap)
        binding.rv.apply {
            adapter = listAdapter
            layoutManager = rvLayoutManager
            addItemDecoration(itemDecorator)
        }
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = rvLayoutManager.childCount
                val totalItemCount = rvLayoutManager.itemCount
                val firstVisibleItemPosition = rvLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    viewModel.loadMoreData()
                }
            }
        })
    }

    private fun setObserver() {
        viewModel.totalList.observe(this) {
            if (it.isNotEmpty()) {
                viewModel.loadMoreData()
            }
        }
        viewModel.dataList.observe(this) { data ->
            listAdapter.addItems(data)
        }
    }

    private fun onTap(dataItem: ResponseDataItem) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", dataItem.id)
        intent.putExtra("body", dataItem.body)
        intent.putExtra("title", dataItem.title)
        startActivity(intent)
    }

    private fun getData() {
        viewModel.getData()
    }

    private val itemDecorator by lazy {
        object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                with(outRect) {
                    bottom = 16
                    right = 16
                }
            }
        }
    }
}