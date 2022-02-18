package com.android.feature_main.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.common.extensions.init
import com.android.common.extensions.initWithGrid
import com.android.common.extensions.initWithSnapping
import com.android.feature_main.presentation.adapter.BestSellerAdapter
import com.android.feature_main.presentation.adapter.HotSalesAdapter
import com.android.feature_main.presentation.adapter.CategoryAdapter
import com.android.feature_main.R
import com.android.feature_main.databinding.FragmentMainBinding
import com.android.feature_main.presentation.viewmodel.MainViewModel
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