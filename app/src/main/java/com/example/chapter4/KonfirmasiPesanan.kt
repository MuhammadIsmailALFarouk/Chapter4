package com.example.chapter4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.databinding.FragmentKonfirmasiPesananBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class KonfirmasiPesanan : Fragment() {
    private lateinit var binding: FragmentKonfirmasiPesananBinding
    private lateinit var modalDataDao:ModalDataDao
    private lateinit var adapterCart: AdapterCart
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

        binding.tv3.text = "Rp. $totalHarga"

        val recyclerViewKeranjang : RecyclerView = binding.recycleKeranjang
        recyclerViewKeranjang.layoutManager = LinearLayoutManager(context)


        modalDataDao =Database.getInstance(requireContext()).modalDataDao
        adapterCart =AdapterCart(requireContext(),modalDataDao)
        recyclerViewKeranjang.adapter =adapterCart
        val dataBase = Database.getInstance(requireContext())
        val dataDao = dataBase.modalDataDao
        dataDao.getAllItem().observe(viewLifecycleOwner, Observer{ listCart ->
            adapterCart.setDataCartList(listCart)


        })


    }

}