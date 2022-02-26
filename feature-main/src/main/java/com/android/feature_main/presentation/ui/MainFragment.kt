package com.android.feature_main.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.common.extensions.init
import com.android.feature_main.presentation.adapter.BestSellerAdapter
import com.android.feature_main.presentation.adapter.CategoryAdapter
import com.android.feature_main.R
import com.android.feature_main.databinding.FragmentMainBinding
import com.android.feature_main.domain.model.BestSeller
import com.android.feature_main.domain.model.HomeStore
import com.android.feature_main.presentation.adapter.HotSalesAdapter
import com.android.feature_main.presentation.viewmodel.MainViewModel
import com.android.feature_main.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main), CategoryAdapter.Listener,
    HotSalesAdapter.Listener, BestSellerAdapter.Listener {

    private val viewModel: MainViewModel by viewModel()
    private val binding: FragmentMainBinding by viewBinding()
    private val categoryAdapter = CategoryAdapter(this)
    private val hotSalesAdapter = HotSalesAdapter(this)
    private val bestSellerAdapter = BestSellerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    private fun initData() {
        viewModel.requestHomeData()
        childFragmentManager.setFragmentResultListener(
            Constants.KEY_DIALOG_FILTER, this
        ) { _, bundle ->
            val brand = bundle.getString(Constants.KEY_BRAND)
            val price = bundle.getString(Constants.KEY_PRICE)
            val size = bundle.getString(Constants.KEY_SIZE)
            showToast("selected brand: $brand, price: $price, size: $size")
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
        initViewPagers()
    }

    private fun initObservers() {
        viewModel.homeData.observe(viewLifecycleOwner) {
            hotSalesAdapter.submitList(it.homeStores)
            bestSellerAdapter.submitList(it.bestSellers)
        }
    }

    private fun initListeners() = with(binding) {
        btnFilter.setOnClickListener {
            FilterDialog().show(childFragmentManager, Constants.TAG_DIALOG_FILTER)
        }
        btnViewAllCategories.setOnClickListener { showToast("View all categories") }
        btnSearchQr.setOnClickListener { showToast("Search qr") }
        btnSeeMoreHotSales.setOnClickListener { showToast("See more hot sales") }
        btnSeeMoreBestSellers.setOnClickListener { showToast("See more best sellers") }
        btnCart.setOnClickListener { showToast("Navigate to cart") }
        btnHeart.setOnClickListener { showToast("Navigate to favorites") }
        btnAccount.setOnClickListener { showToast("Navigate to account") }
    }

    private fun initViewPagers() = with(binding) {
        recyclerCategories.init(categoryAdapter)
        with(recyclerHotSales) {
            init(hotSalesAdapter)
            LinearSnapHelper().attachToRecyclerView(this)
            setInfinite(true)
            setFlat(true)
        }
        with(recyclerBestSeller) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = bestSellerAdapter
        }
    }

    override fun onItemClick(item: String) {
        showToast("Clicked $item")
    }

    override fun onItemClick(item: HomeStore) {
        showToast("Clicked $item")
    }

    override fun onItemClick(item: BestSeller) {
        showToast("Clicked $item")
    }
}