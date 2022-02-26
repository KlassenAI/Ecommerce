package com.android.feature_main.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.android.feature_main.databinding.DialogFilterBinding
import com.android.feature_main.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterDialog : BottomSheetDialogFragment() {

    init {
        isCancelable = false
    }

    private var binding: DialogFilterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?
    ): View {
        binding = DialogFilterBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() = with(binding!!) {
        btnCancel.setOnClickListener { dismiss() }
        btnDone.setOnClickListener {
            val bundle = bundleOf(
                Constants.KEY_BRAND to spinnerBrand.selectedItem as String,
                Constants.KEY_PRICE to spinnerPrice.selectedItem as String,
                Constants.KEY_SIZE to spinnerSize.selectedItem as String
            )
            parentFragmentManager.setFragmentResult(Constants.KEY_DIALOG_FILTER, bundle)
            dismiss()
        }
    }
}