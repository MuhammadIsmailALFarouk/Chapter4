package com.example.chapter4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.databinding.ListMenuGridBinding
import com.example.chapter4.databinding.ListMenuLinearBinding

class MyAdapter(private val listMenu: ArrayList<ModalData>, val isGrid:Boolean)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var onItemClick : ((ModalData) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (isGrid) {
            val binding =
                ListMenuGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GridMenuHolder(binding)
        } else {
            val binding =
                ListMenuLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LinearMenuHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isGrid) {
            val gridHolder = holder as GridMenuHolder
            gridHolder.onBind(listMenu[position])
        } else {
            val linearHolder = holder as LinearMenuHolder
            linearHolder.onBind(listMenu[position])
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listMenu[position])
        }

    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}

class GridMenuHolder(val binding: ListMenuGridBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: ModalData) {
        binding.imageDefault.setImageResource(data.image)
        binding.namaMakanan.text = data.namaMakanan
        binding.hargaMakanan.text = data.hargaMakanan
    }
}

class LinearMenuHolder(val binding: ListMenuLinearBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: ModalData) {
        binding.imageDefault.setImageResource(data.image)
        binding.namaMakanan.text = data.namaMakanan
        binding.hargaMakanan.text = data.hargaMakanan
    }
}
