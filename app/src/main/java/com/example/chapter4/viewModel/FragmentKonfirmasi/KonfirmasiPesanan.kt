package com.example.chapter4.viewModel.FragmentKonfirmasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.R
import com.example.chapter4.cart.AdapterCart
import com.example.chapter4.databaseRom.Database
import com.example.chapter4.databaseRom.ModalDataDao
import com.example.chapter4.databinding.FragmentKonfirmasiPesananBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class KonfirmasiPesanan : Fragment() {
    private lateinit var binding: FragmentKonfirmasiPesananBinding
    private lateinit var modalDataDao: ModalDataDao
    private lateinit var adapterCart: AdapterCart
    private lateinit var viewModel:ViewModelKonfirmasi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKonfirmasiPesananBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.visibility = View.GONE
        val totalHarga = arguments?.getInt("TotalHarga")

        binding.tv3.text = "Total Pembayaran Rp. $totalHarga"

        val recyclerViewKeranjang : RecyclerView = binding.recycleKeranjang
        recyclerViewKeranjang.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this).get(ViewModelKonfirmasi::class.java)

        viewModel.setTotalHarga(totalHarga ?: 0)

        viewModel.getTotalHarga().observe(viewLifecycleOwner, Observer{ harga ->
            binding.tv3.text = "Total Pembayaran Rp. $harga"
        })

        modalDataDao = Database.getInstance(requireContext()).modalDataDao
        adapterCart = AdapterCart(requireContext(),modalDataDao)
        recyclerViewKeranjang.adapter =adapterCart
        val dataBase = Database.getInstance(requireContext())
        val dataDao = dataBase.modalDataDao
        dataDao.getAllItem().observe(viewLifecycleOwner, Observer{ listCart ->
            adapterCart.setDataCartList(listCart)

        })
        binding.pesan.setOnClickListener {
            Toast.makeText(requireContext(), "Pesanan Berhasil Di Proses", Toast.LENGTH_SHORT).show()
            viewModel.deleteAllItemsFromCart(dataDao)
            dataDao.deleteAllItemCart()
            val nBundle = Bundle()
            findNavController().navigate(R.id.action_konfirmasiPesanan_to_homeFragment, nBundle)
        }


    }

}