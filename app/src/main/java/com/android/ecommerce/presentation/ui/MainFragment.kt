package com.android.ecommerce.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.ecommerce.R
import com.android.ecommerce.databinding.FragmentMainBinding
import com.android.ecommerce.extensions.init
import com.android.ecommerce.extensions.initWithGrid
import com.android.ecommerce.extensions.initWithSnapping
import com.android.ecommerce.presentation.adapter.BestSellerAdapter
import com.android.ecommerce.presentation.adapter.HotSalesAdapter
import com.android.ecommerce.presentation.adapter.CategoryAdapter
import com.android.ecommerce.presentation.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main), CategoryAdapter.Listener {

    private val viewModel: MainViewModel by viewModel()
    private val binding: FragmentMainBinding by viewBinding()
    private val categoryAdapter = CategoryAdapter(this)
    private val hotSalesAdapter = HotSalesAdapter()
    private val bestSellerAdapter = BestSellerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclers()
        initButtons()
        initData()
        initObservers()
    }

    private fun initRecyclers() = with(binding) {
        recyclerCategories.init(categoryAdapter)
        recyclerHotSales.initWithSnapping(hotSalesAdapter)
        recyclerHotSales.apply {
            setInfinite(true)
            setFlat(true)
        }
        recyclerBestSeller.initWithGrid(bestSellerAdapter)
    }

    private fun initButtons() = with(binding) {
        btnFilter.setOnClickListener {
            FilterBottomSheetDialog().show(childFragmentManager, "dialog")
        }
    }

    private fun initData() {
        viewModel.requestHome()
    }

    private fun initObservers() {
        viewModel.checkedItem.observe(viewLifecycleOwner) { categoryAdapter.setCheckedItem(it) }
        viewModel.hotSales.observe(viewLifecycleOwner) { hotSalesAdapter.submitList(it) }
        viewModel.bestSellers.observe(viewLifecycleOwner) { bestSellerAdapter.submitList(it) }
    }

    override fun setCheckedItem(position: Int) {
        viewModel.saveCheckedItem(position)
    }
}