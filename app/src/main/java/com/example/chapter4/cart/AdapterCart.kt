package com.example.chapter4.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chapter4.databaseRom.ModalDataDao
import com.example.chapter4.R

class AdapterCart (private val contex: Context,private val dataCart: ModalDataDao) :
    RecyclerView.Adapter<AdapterCart.CartViewHolder>() {
    private var listCart:List<Cart> = emptyList()

    fun getDataList():List<Cart>{
        return listCart
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_keranjang, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val current = listCart[position]

        holder.name.text = current.namaMakanan
        Glide
            .with(holder.itemView)
            .load(current.image)
            .centerCrop()
            .into(holder.image)

        holder.quantity.text="${current.quantity}"

        var totalPembayaran = current.hargaMakanan?.toInt()?.times(current.quantity)
        holder.harga.text = "Rp. ${totalPembayaran}"




        // Tombol untuk menghapus item
        holder.delete.setOnClickListener {
            deleteCart(current)
            notifyItemRemoved(position)
        }


       // Menambahkan Jumlah Makanan
        holder.plus.setOnClickListener {
            current.quantity++
            updateDataCart(current)
            notifyItemChanged(position)
        }

       // mengurangi jumlah makanan
        holder.minus.setOnClickListener {
            if (current.quantity < 1){
                deleteCart(current)
                notifyItemRemoved(position)
            }else{
                current.quantity--
                updateDataCart(current)
                notifyItemChanged(position)
            }
        }

    }
    
    override fun getItemCount(): Int {
        return listCart.size
    }
    fun getTotalHarga(): Int {
        var totalHarga = 0

        for (item in listCart) {
            val hargaMakanan = item.hargaMakanan?.toInt() ?: 0
            totalHarga += hargaMakanan * item.quantity
        }

        return totalHarga
    }

    fun setDataCartList(listCart: List<Cart>) {
        this.listCart = listCart
        notifyDataSetChanged()

    }

    private fun updateDataCart(cart: Cart) {
        dataCart.updateQuantityByItemId(cart.quantity, cart.id)
    }

    private fun deleteCart(cart: Cart) {
        dataCart.delteByItemId(cart.id)
    }

    class CartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.tv_namaMenu)
        val image: ImageView = itemView.findViewById(R.id.imageDefault)
        val harga: TextView = itemView.findViewById(R.id.tv_hargaMenu)
        val quantity: TextView = itemView.findViewById(R.id.tvCounter)
        val plus: ImageView = itemView.findViewById(R.id.plus)
        val minus: ImageView = itemView.findViewById(R.id.minus)
        val delete: ImageView = itemView.findViewById(R.id.delete)
    }
}