package com.android.feature_main.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.feature_main.R
import com.android.feature_main.databinding.BottomSheetFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheetDialog : BottomSheetDialogFragment() {

    init {
        isCancelable = false
    }

    private val binding: BottomSheetFilterBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        initSpinners()
        initButtons()
    }

    private fun initSpinners() = with(binding) {
        spinnerBrand.adapter = SpinnerAdapter(requireContext(), listOf("Samsung", "Xiaomi")).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinnerPrice.adapter = SpinnerAdapter(requireContext(), listOf("$0-$300", "$300-$500", "$500-$10000")).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinnerSize.adapter = SpinnerAdapter(requireContext(), listOf("less 4.5 inches", "4.5 to 5.5 inches", "more 5.5 inches")).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private fun initButtons() = with(binding) {
        btnCancel.setOnClickListener {
            dialog?.dismiss()
        }
        btnDone.setOnClickListener {
            //вернуть значения фильтра
            dialog?.dismiss()
        }
    }

    inner class SpinnerAdapter(context: Context, private val items: List<String>) :
        ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items) {

        private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View =
                convertView ?: layoutInflater.inflate(R.layout.item_spinner, parent, false)
            view.findViewById<TextView>(R.id.spinner_title).text = getItem(position)
            return view
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getDropDownView(position, convertView, parent)
            view.setPadding(28, 0, 0, 0)
            return view
        }

        override fun getCount() = items.size

        override fun getItem(position: Int): String = items[position]

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
    }
}