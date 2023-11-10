package com.example.chapter4.viewModel.FragmentKeranjang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.R
import com.example.chapter4.cart.AdapterCart
import com.example.chapter4.databaseRom.Database
import com.example.chapter4.databaseRom.ModalDataDao
import com.example.chapter4.databinding.FragmentKeranjangBinding


class Keranjang : Fragment() {
    private lateinit var binding : FragmentKeranjangBinding
    private lateinit var modalDataDao: ModalDataDao
    private lateinit var adapterCart: AdapterCart
    private lateinit var viewModelKeranjang: ViewModelKeranjang

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKeranjangBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewKeranjang: RecyclerView = binding.recycleKeranjang
        recyclerViewKeranjang.layoutManager = LinearLayoutManager(context)
//        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
//        bottomNav.visibility = View.GONE

        viewModelKeranjang = ViewModelProvider(this).get(ViewModelKeranjang::class.java)
        modalDataDao = Database.getInstance(requireContext()).modalDataDao
        adapterCart = AdapterCart(requireContext(), modalDataDao)
        recyclerViewKeranjang.adapter = adapterCart
        viewModelKeranjang.initialize(modalDataDao, adapterCart)

        val totalHargaObserver = Observer<Int> { totalHarga ->
            binding.tvTotalHargaCart.text = "Rp. $totalHarga"
        }
        viewModelKeranjang.getTotalHarga().observe(viewLifecycleOwner, totalHargaObserver)

        viewModelKeranjang.updateData()

        binding.btnPesanToKonfirmasi.setOnClickListener {
            viewModelKeranjang.getTotalHarga().value?.let { totalHarga ->
                val nBundle = Bundle()
                nBundle.putInt("TotalHarga", totalHarga)
                findNavController().navigate(R.id.action_keranjang_to_konfirmasiPesanan, nBundle)
            }
        }
    }
//        modalDataDao = Database.getInstance(requireContext()).modalDataDao
//        adapterCart = AdapterCart(requireContext(),modalDataDao)
//        recyclerViewKeranjang.adapter =adapterCart
//
//
//        val dataBase = Database.getInstance(requireContext())
//        val dataDao = dataBase.modalDataDao
//
//        dataDao.getAllItem().observe(viewLifecycleOwner,Observer{listCart ->
//            adapterCart.setDataCartList(listCart)
//
//            val totalHarga = adapterCart.getTotalHarga()
//            binding.tvTotalHargaCart.text = "Rp. $totalHarga"
//
//            binding.btnPesanToKonfirmasi.setOnClickListener {
//                val nBundle = Bundle()
//                nBundle.putInt("TotalHarga",totalHarga)
//                findNavController().navigate(R.id.action_keranjang_to_konfirmasiPesanan, nBundle)
//
//            }
//
//
//        })

//    }


}