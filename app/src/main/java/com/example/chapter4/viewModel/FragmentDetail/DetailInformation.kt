package com.example.chapter4.viewModel.FragmentDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.chapter4.R
import com.example.chapter4.cart.Cart
import com.example.chapter4.databaseRom.Database
import com.example.chapter4.databinding.FragmentDetailInformationBinding
import com.example.chapter4.listmenu.Data
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailInformation : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var _binding:FragmentDetailInformationBinding
    private lateinit var appDb: Database
    private val binding get() = _binding
    private var mcounter:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailInformationBinding.inflate(inflater,container,false)

        return binding.root;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclickLocation()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.visibility = View.GONE
        val makanan = arguments?.getParcelable<Data>("ModalData")
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if(makanan != null){
            val namaMakanan : TextView = binding.namaMakanan
            val gambarMakanan: ImageView = binding.ivImage1
            val hargaMakanan : TextView = binding.hargaMakanan
            val deskripsi: TextView = binding.description
            val alamatToko: TextView = binding.alamatToko

            namaMakanan.text = makanan.nama
            Glide
                .with(requireActivity())
                .load(makanan.imageUrl)
                .centerCrop()
                .into(gambarMakanan)
            hargaMakanan.text = "Rp. ${makanan.harga}"
            deskripsi.text = makanan.detail
            alamatToko.text = makanan.alamatResto

        }
        withViewModels()
        viewModel.counter1.observe(viewLifecycleOwner){
            if (makanan?.harga != null){
                var numericPart = makanan.harga.toString().replace("[^0-9]".toRegex(), "")
                binding.tambahKeranjang.text = "Tambah Ke Keranjang - Rp. ${it*numericPart.toInt()}"
            }
        }
        binding.tambahKeranjang.setOnClickListener {
            val jumlahPesanan = binding.tvCounter.text.toString().toInt()

            if(binding.tvCounter.text.toString().toInt() > 0){
                val cekItem =Database.getInstance(requireContext()).modalDataDao.getItemByName(makanan?.nama.toString()) // cek apakah menu sudha ada
                if (cekItem != null) {
                    // jika menu sudah ada maka tambahkan quantitynya
                    cekItem.quantity += jumlahPesanan
                    Database.getInstance(requireContext()).modalDataDao.updateQuantityByItemId(cekItem.quantity, cekItem.id)
                } else {
                    // jika menu  belum ada maka tambahkan menu baru
                    Database.getInstance(requireContext()).modalDataDao.insert(Cart(0,makanan?.nama,makanan?.harga?.toString()?.replace("[^0-9]".toRegex(), "",),makanan?.imageUrl,binding.tvCounter.text.toString().toInt()))
                }
//                Database.getInstance(requireContext()).modalDataDao.insert(Cart(0,makanan?.nama,makanan?.harga?.toString()?.replace("[^0-9]".toRegex(), "",),makanan?.imageUrl,binding.tvCounter.text.toString().toInt()))
                Toast.makeText(requireContext(),"Pesanan Berhasil Di pindahkan ke keranjang Anda",Toast.LENGTH_LONG).show()
                val nBundle = Bundle()
                findNavController().navigate(R.id.action_detailInformation_to_homeFragment2, nBundle)
            }else{
                Toast.makeText(requireContext(),"Minimal Pesan Harus 1 buah",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setOnclickLocation(){
        view?.findViewById<TextView>(R.id.tv_1)?.setOnClickListener{
            navigateToMaps()
        }
    }

    private fun navigateToMaps(){
        val b = Intent(
            Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/h4wQKqaBuXzftGK77")
        )
        startActivity(b)
    }
    private fun withViewModels(){
        binding.plus.setOnClickListener{
            mIncrement()
        }
        binding.minus.setOnClickListener{
            mDecrement()
        }
        viewModel.counter.observe(viewLifecycleOwner){result ->
            binding.tvCounter.text = result.toString()

        }
    }
    private fun mIncrement(){
        viewModel.incremet()
    }
    private fun mDecrement(){
        viewModel.decrement()
    }

}