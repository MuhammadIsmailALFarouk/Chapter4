package com.example.chapter4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter4.databinding.FragmentHomeBinding
import com.example.chapter4.listmenu.Data
import com.example.chapter4.repository.RepositoryMenu
import com.example.chapter4.repository.ViewModelAdapterFactory
import com.example.chapter4.viewModel.FragmentDetail.SharedPreference
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreference
    private var listMenu: ArrayList<Data> = ArrayList()
    private lateinit var binding:FragmentHomeBinding
//    private val viewModel:ViewModelAdapter by viewModels()
    private var isGrid =true


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

        //setupRecyclerView(isGrid,listMenu)
        setupActionChangeLayout()
        val repositroyMenu = RepositoryMenu()
        val viewModelFactory = ViewModelAdapterFactory(repositroyMenu)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ViewModelAdapter::class.java)


        viewModel.getListMenu()
        binding.listMenu1.setOnClickListener {
            viewModel.getListMenu("burger")
        }
        binding.menuMie.setOnClickListener {
            viewModel.getListMenu("mie")
        }
        binding.snack.setOnClickListener {
            viewModel.getListMenu("snack")
        }
        binding.minuman.setOnClickListener {
            viewModel.getListMenu("minuman")
        }
        viewModel.listMenu.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                setupRecyclerView(isGrid, it as ArrayList<Data>)
                listMenu.clear()
                listMenu.addAll(it)
            }
        }
    }
    fun setupRecyclerView(isGrid: Boolean, data: ArrayList<Data>) {
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