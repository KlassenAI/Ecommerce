package com.android.feature_cart.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.core.adapter.HorizontalSpaceItemDecoration
import com.android.core.extensions.init
import com.android.core.extensions.log
import com.android.core.extensions.navigateUp
import com.android.core.extensions.showToast
import com.android.feature_cart.R
import com.android.feature_cart.databinding.FragmentCartBinding
import com.android.feature_cart.presentation.adapter.BasketItemAdapter
import com.android.feature_cart.presentation.viewmodel.CartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment(R.layout.fragment_cart), BasketItemAdapter.Listener {

    private val basketItemAdapter = BasketItemAdapter(this)
    private val binding: FragmentCartBinding by viewBinding()
    private val viewModel: CartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    private fun initData() = viewModel.requestCart()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initListeners()
        initObserver()
    }

    private fun initRecycler() = binding.recycler.init(basketItemAdapter, false)

    private fun initListeners() = with(binding) {
        btnBack.setOnClickListener { navigateUp() }
        btnNavigation.setOnClickListener { showToast("Add address") }
        btnCheckout.setOnClickListener { showToast("checkout") }
    }

    private fun initObserver() = viewModel.cart.observe(viewLifecycleOwner) {
        basketItemAdapter.submitList(it.basket)
        binding.total.text = getString(R.string.price_with_dollar, it.total)
        binding.delivery.text = it.delivery
    }

    override fun onCheckoutChanged(checkout: Int) {
        binding.total.text = getString(R.string.price_with_dollar, checkout)
    }
}