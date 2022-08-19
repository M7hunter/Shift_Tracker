package com.m7.shifttracker.addShift

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.m7.shifttracker.R
import com.m7.shifttracker.databinding.ItemOptionBinding

class OptionsAdapter(
    private var dataList: List<String>,
    private val onOptionClicked: (option: String) -> Unit
) : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_option,
                parent,
                false
            )
        ).apply {
            itemBinding.root.setOnClickListener {
                onOptionClicked.invoke(dataList[adapterPosition])
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(val itemBinding: ItemOptionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemData: String) {
            itemBinding.optionTitle = itemData
        }
    }
}