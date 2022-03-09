package com.android.feature_main.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.core.extensions.init
import com.android.core.extensions.navigate
import com.android.core.extensions.showToast
import com.android.core.navigation.NavCommand
import com.android.core.navigation.NavCommands
import com.android.core.utils.Constants
import com.android.feature_main.presentation.adapter.BestSellerAdapter
import com.android.feature_main.presentation.adapter.CategoryAdapter
import com.android.feature_main.R
import com.android.feature_main.databinding.FragmentMainBinding
import com.android.feature_main.domain.model.BestSeller
import com.android.feature_main.domain.model.HomeStore
import com.android.feature_main.presentation.adapter.HotSalesAdapter
import com.android.feature_main.presentation.viewmodel.MainViewModel
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
        viewModel.productCount.observe(viewLifecycleOwner) {
            binding.productCount.text = it.toString()
            binding.productCount.isVisible = it > 0
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
        btnCart.setOnClickListener {
            navigate(
                NavCommand(
                    target = NavCommands.DeepLink(
                        url = Uri.parse("jetnavapp://cart"),
                        isModal = false,
                        isSingleTop = true
                    )
                )
            )
        }
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
        navigate(
            NavCommand(
                target = NavCommands.DeepLink(
                    url = Uri.parse("jetnavapp://details"),
                    isModal = false,
                    isSingleTop = true
                )
            )
        )
    }
}