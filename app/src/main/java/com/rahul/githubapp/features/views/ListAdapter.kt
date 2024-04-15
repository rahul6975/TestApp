package com.rahul.githubapp.features.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.githubapp.databinding.ItemLayoutBinding
import com.rahul.githubapp.features.model.ResponseDataItem

class ListAdapter(
    private val dataList: ArrayList<ResponseDataItem>,
    val callback: (ResponseDataItem) -> Unit
) :
    RecyclerView.Adapter<ListAdapter.VH>() {

    inner class VH(private val view: ItemLayoutBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(data: ResponseDataItem) {
            view.tvId.text = data.id.toString()
            view.tvTitle.text = data.title
            view.root.setOnClickListener {
                callback.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                null,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun addItems(newItems: List<ResponseDataItem>) {
        val oldSize = dataList.size
        dataList.addAll(newItems)
        notifyItemRangeInserted(oldSize, newItems.size)
    }

}