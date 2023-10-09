package com.example.chapter4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.prefs.Preferences

class HomeFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreference
    private lateinit var recycleview: RecyclerView
    private lateinit var listMenu: ArrayList<ModalData>
    private lateinit var myAdapter: MyAdapter
    private lateinit var binding:FragmentHomeBinding
    private var isGrid =true
    private var sharedBoolean = "grid"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = SharedPreference(requireContext())
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        if (bottomNav != null){
            bottomNav.visibility = View.VISIBLE
        }

        isGrid = sharedPreferences.isGrid
        listMenu = arrayListOf(

            ModalData(
                "Ayam Bakar",
                "Rp. 35.000",
                R.drawable.ayam_spicy,
                "Ayam Spicy Ayam Pilihan kita semua",
                "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345"
            ),
            ModalData(
                "Ayam Spicy",
                "Rp. 45.000",
                R.drawable.ayam_spicy,
                "Ayam Crispy adalah hidangan yang sangat populer di seluruh dunia, terutama di restoran cepat saji dan warung makan. Hidangan ini memiliki ciri khas ayam yang digoreng hingga menjadi renyah dan garing di luar, sementara dagingnya tetap lembut dan juicy di dalam.",
                "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345"
            ),
            ModalData(
                "Paket Hemat",
                "Rp. 70.000",
                R.drawable.paket_hemat,
                "Paket Hemat ini di rekomendasikan untuk yang makannya banyak karena mencakup beberapa variasi menu",
                "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345"
            ),
            ModalData(
                "Pizza",
                "Rp. 80.000",
                R.drawable.pizza,
                "Pizza adalah hidangan yang terkenal di seluruh dunia dan memiliki banyak variasi rasa dan topping",
                "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345"
            ),
            ModalData(
                "Sushi",
                "Rp. 95.000",
                R.drawable.sushi,
                "Sushi adalah hidangan tradisional Jepang yang terkenal di seluruh dunia. Ini adalah hidangan yang menggabungkan nasi yang dicampur dengan cuka beras, dilapisi dengan berbagai bahan seperti ikan, daging laut, sayuran, dan saus",
                "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345"
            ),
            ModalData(
                "Ayam Bakar",
                "Rp. 50.000",
                R.drawable.sushi,
                "Ayam Spicy Ayam Pilihan kita semua",
                "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345"
            ),
            ModalData(
                "Ayam Bakar",
                "Rp. 50.000",
                R.drawable.sushi,
                "Ayam Spicy Ayam Pilihan kita semua",
                "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345"
            ),
        )

        setupRecyclerView(isGrid,listMenu)
        setupActionChangeLayout()
    }
    fun setupRecyclerView(isGrid: Boolean, data: ArrayList<ModalData>) {
        val adapterMenu = MyAdapter(data, isGrid)
        binding.recycleview.adapter = adapterMenu
        if (isGrid) {
            binding.recycleview.layoutManager = GridLayoutManager(context, 2)
        } else {
            binding.recycleview.layoutManager = LinearLayoutManager(context)
        }

        adapterMenu.onItemClick = {
            val nBundle = Bundle()
            nBundle.putParcelable("ModalData", it)
            findNavController().navigate(R.id.action_homeFragment_to_detailInformation, nBundle)
        }
    }
    fun setupActionChangeLayout() {
        //button diklik
        binding.changelist.setOnClickListener {
            isGrid = !isGrid
            sharedPreferences.isGrid = isGrid
            setupRecyclerView(isGrid, listMenu)
        }
    }


}