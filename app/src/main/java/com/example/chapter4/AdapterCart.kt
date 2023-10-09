package com.example.chapter4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.databinding.ListMenuLinearBinding

class AdapterCart (private val listCart: ArrayList<Cart>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((ModalData) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListMenuLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinearMenuHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val linearHolder = holder as LinearMenuHolder

    }

    override fun getItemCount(): Int {
        return listCart.size
    }
}