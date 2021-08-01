package com.umbrella.simplepattern.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umbrella.simplepattern.databinding.RecyclerViewItemBinding
import com.umbrella.simplepattern.model.SomeObject

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var list: List<SomeObject> = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null;

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setList(list: List<SomeObject>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(private val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(someObject: SomeObject) {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it.onItemClick(adapterPosition)
                }
            }
        }
    }
}