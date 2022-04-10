package com.android.feature_details.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.size
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.core.adapter.HorizontalSpaceItemDecoration
import com.android.core.extensions.*
import com.android.core.navigation.NavCommand
import com.android.core.navigation.NavCommands
import com.android.feature_details.R
import com.android.feature_details.databinding.FragmentDetailsBinding
import com.android.feature_details.presentation.adapter.*
import com.android.feature_details.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val imageAdapter = ImageAdapter()
    private val starAdapter = StarAdapter()
    private val colorAdapter = ColorAdapter()
    private val capacityAdapter = CapacityAdapter()
    private val binding: FragmentDetailsBinding by viewBinding()
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    private fun initData() = viewModel.requestDetails()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclers()
        initListeners()
        initObserver()
    }

    private fun initRecyclers() = with(binding) {
        with(imageRecycler) {
            init(imageAdapter)
            setInfinite(true)
            setIntervalRatio(0.6f)
        }
        starRecycler.init(starAdapter)
        with(colorRecycler) {
            init(colorAdapter)
            addItemDecoration(HorizontalSpaceItemDecoration(19))
        }
        with(capacityRecycler) {
            init(capacityAdapter)
            addItemDecoration(HorizontalSpaceItemDecoration(20))
        }
    }

    private fun initListeners() = with(binding) {
        btnBack.setOnClickListener { navigateUp() }
        btnCart.setOnClickListener { navigateToCart() }
        btnHeart.setOnClickListener { showToast("click heart") }
        btnAdd.setOnClickListener {
            if (colorAdapter.selected == null) {
                showToast("Не выбран цвет телефона")
                return@setOnClickListener
            } else if (capacityAdapter.selected == null) {
                showToast("Не выбрана память телефона")
                return@setOnClickListener
            }
            viewModel.addProduct(colorAdapter.selected!!, capacityAdapter.selected!!)
        }
        tabs.setOnCheckedChangeListener { radioGroup, checkedId ->
            log("$checkedId")
            for (i in 0 until radioGroup.size) {
                val item = radioGroup.getChildAt(i)
                if (item is RadioButton) {
                    if (item.id != checkedId) {
                        item.typeface = ResourcesCompat.getFont(requireContext(), com.android.core.R.font.mark_pro_400)
                    } else {
                        item.typeface = ResourcesCompat.getFont(requireContext(), com.android.core.R.font.mark_pro_700)
                    }
                }
            }
        }
        colorAdapter.setOnItemClickListener { showToast("Clicked $it") }
        capacityAdapter.setOnItemClickListener { showToast("Clicked $it") }
    }

    private fun initObserver() = with(binding) {
        viewModel.product.observe(viewLifecycleOwner) {
            imageAdapter.submitList(it.images)
            starAdapter.submitList(it.rating)
            colorAdapter.submitList(it.color)
            capacityAdapter.submitList(it.capacity)
            cpu.text = it.cpu
            camera.text = it.camera
            sd.text = it.sd
            ssd.text = it.ssd
            btnAdd.text = getString(R.string.add_to_cart_with_price, it.price)
        }
    }
}