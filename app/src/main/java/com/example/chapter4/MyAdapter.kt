package com.example.chapter4

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.databinding.ListMenuGridBinding
import com.example.chapter4.databinding.ListMenuLinearBinding
import com.example.chapter4.listmenu.Data

class MyAdapter(private val listMenu: ArrayList<Data>, val isGrid:Boolean)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var onItemClick : ((Data) -> Unit)? = null


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
    fun onBind(data: Data) {
        Glide
            .with(itemView)
            .load(data.imageUrl)
            .centerCrop()
            .into(binding.imageDefault)
        binding.namaMakanan.text = data.nama
        binding.hargaMakanan.text = "Rp. ${data.harga}"
    }
}

class LinearMenuHolder(val binding: ListMenuLinearBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Data) {
        Glide
            .with(itemView)
            .load(data.imageUrl)
            .centerCrop()
            .into(binding.imageDefault)
        binding.namaMakanan.text = data.nama
        binding.hargaMakanan.text = "Rp. ${data.harga}"
    }
}
