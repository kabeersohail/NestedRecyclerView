package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.EachItemBinding
import java.util.ArrayList

class ItemAdapter(private val mList: ArrayList<DataModel>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var list: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EachItemBinding.inflate(layoutInflater, parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = mList[position]
        holder.mTextView.text = model.itemText
        val isExpandable: Boolean = model.isExpandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        val adapter = NestedAdapter(list)
        holder.nestedRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.nestedRecyclerView.setHasFixedSize(true)
        holder.nestedRecyclerView.adapter = adapter

        if (isExpandable) {
            holder.mArrowImage.setImageResource(R.drawable.arrow_up)
        } else {
            holder.mArrowImage.setImageResource(R.drawable.arrow_down)
        }

        holder.linearLayout.setOnClickListener {
            model.isExpandable = !model.isExpandable
            list = model.nestedList
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ItemViewHolder(binding: EachItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val linearLayout: LinearLayout = binding.linearLayout
        val expandableLayout: RelativeLayout = binding.expandableLayout
        val mTextView: TextView = binding.itemTv
        val mArrowImage: ImageView = binding.arroImageview
        val nestedRecyclerView: RecyclerView = binding.childRv

    }
}