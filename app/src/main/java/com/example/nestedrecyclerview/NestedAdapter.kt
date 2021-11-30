package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.NestedItemBinding

class NestedAdapter(private val mList: List<String>) :
    RecyclerView.Adapter<NestedAdapter.NestedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NestedItemBinding.inflate(layoutInflater, parent,false)
        return NestedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        holder.mTv.text = mList[position]
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class NestedViewHolder(binding: NestedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val mTv: TextView = binding.nestedItemTv
    }
}