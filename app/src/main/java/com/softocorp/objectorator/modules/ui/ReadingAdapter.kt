package com.softocorp.objectorator.modules.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softocorp.objectorator.databinding.FacialRecyclerContainerBinding
import com.softocorp.objectorator.model.ReadingModel

class ReadingAdapter(var list: ArrayList<ReadingModel>) : RecyclerView.Adapter<ReadingAdapter.ReadingViewHolder>() {

    inner class ReadingViewHolder(val binding: FacialRecyclerContainerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(element: ReadingModel) {
            binding.apply {
                readingNameTxtview.text = element.readingText
                readingNameDesc.text = element.contentText
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingViewHolder {
        return ReadingViewHolder(FacialRecyclerContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReadingViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}