package com.example.fashionday.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fashionday.R
import com.example.fashionday.RvClickListener
import com.example.fashionday.data.response.DataItem
import com.example.fashionday.databinding.ListBestItemBinding

class BestTodayAdapter(private val listBest: List<DataItem>) : RecyclerView.Adapter<BestTodayAdapter.ListViewHolder>() {

    var listener: RvClickListener? = null

    class ListViewHolder(private val binding: ListBestItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataItem, listener: RvClickListener?) {
            binding.apply {
                Glide.with(itemView.context).load(data.photo).into(ivBest)
                ivBest.setOnClickListener{
                    listener?.onItemClicked(it, data)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ListBestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listBest.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataItem = listBest[position]
        holder.bind(dataItem, listener)

    }
}