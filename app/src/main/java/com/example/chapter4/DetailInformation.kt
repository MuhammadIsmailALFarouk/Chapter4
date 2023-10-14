package com.example.chapter4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.chapter4.databinding.FragmentDetailInformationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailInformation : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var _binding:FragmentDetailInformationBinding
    private lateinit var appDb:Database
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
        val makanan = arguments?.getParcelable<ModalData>("ModalData")

        if(makanan != null){
            val namaMakanan : TextView = binding.namaMakanan
            val gambarMakanan: ImageView = binding.ivImage1
            val hargaMakanan : TextView = binding.hargaMakanan
            val deskripsi: TextView = binding.description
            val alamatToko: TextView = binding.alamatToko

            namaMakanan.text = makanan.namaMakanan
            gambarMakanan.setImageResource(makanan.image)
            hargaMakanan.text = makanan.hargaMakanan
            deskripsi.text = makanan.deskripsi
            alamatToko.text = makanan.alamatToko

        }


        withViewModels()

        binding.tambahKeranjang.setOnClickListener {
            Database.getInstance(requireContext()).modalDataDao.insert(Cart(0,makanan?.namaMakanan,makanan?.hargaMakanan?.replace("[^0-9]".toRegex(), "",),makanan?.image,binding.tvCounter.text.toString().toInt()))

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
        viewModel.counter.observe(this){result ->
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