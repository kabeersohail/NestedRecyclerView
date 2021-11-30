package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.ParentItemBinding
import java.util.ArrayList

class ParentAdapter(private val mList: ArrayList<DataModel>) : RecyclerView.Adapter<ParentAdapter.ItemViewHolder>() {

    private var list: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ParentItemBinding.inflate(layoutInflater, parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = mList[position]
        holder.mTextView.text = model.itemText
        val isExpandable: Boolean = model.isExpandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        val adapter = SingleChildAdapter(list)
        holder.nestedRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.nestedRecyclerView.setHasFixedSize(true)
        holder.nestedRecyclerView.adapter = adapter

        holder.constraintLayout.setOnClickListener {
            model.isExpandable = !model.isExpandable
            list = model.nestedList
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ItemViewHolder(binding: ParentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val constraintLayout: ConstraintLayout = binding.constraintLayout
        val expandableLayout: RelativeLayout = binding.expandableLayout
        val mTextView: TextView = binding.itemTv
        val nestedRecyclerView: RecyclerView = binding.childRv

    }
}