package com.m7.shifttracker.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.m7.shifttracker.R
import com.m7.shifttracker.Shift
import com.m7.shifttracker.databinding.ItemShiftBinding

class ShiftAdapter(
    private var dataList: MutableList<Shift>
) : RecyclerView.Adapter<ShiftAdapter.ViewHolder>() {

    fun addItem(newItem: Shift) {
        dataList.add(0, newItem)
        notifyItemInserted(0)
    }

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_shift,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(private val itemBinding: ItemShiftBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(itemData: Shift) {
            itemBinding.apply {
                shift = itemData
            }
        }
    }
}