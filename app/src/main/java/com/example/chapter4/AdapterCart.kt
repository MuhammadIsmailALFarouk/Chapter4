package com.example.chapter4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.databinding.ListKeranjangBinding
import com.example.chapter4.databinding.ListMenuLinearBinding

class AdapterCart (private val contex: Context,private val dataCart:ModalDataDao) :
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
        holder.image.setImageResource(current.image?:R.drawable.image_default)
        holder.quantity.text="${current.quantity}"


       // Menambahkan Jumlah Makanan
        holder.plus.setOnClickListener {
            current.quantity++
            updateDataCart(current)
        }

       // mengurangi jumlah makanan
        holder.minus.setOnClickListener {
            if (current.quantity > 1) {
               current.quantity--
                updateDataCart(current)
            }
        }


        // Tombol untuk menghapus item
        holder.delete.setOnClickListener {
            deleteCart(current)
        }

    }
    
    override fun getItemCount(): Int {
        return listCart.size
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
        val price: TextView = itemView.findViewById(R.id.tv_hargaMenu)
        val quantity: TextView = itemView.findViewById(R.id.tvCounter)
        val plus: ImageView = itemView.findViewById(R.id.plus)
        val minus: ImageView = itemView.findViewById(R.id.minus)
        val delete: ImageView = itemView.findViewById(R.id.delete)
    }
}