package com.m7.shifttracker.addShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.m7.shifttracker.R
import com.m7.shifttracker.databinding.OptionsBottomSheetBinding

class OptionsBottomSheet(
    private val options: List<String>,
    private val onOptionClicked: (option: String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: OptionsBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OptionsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvOptions.layoutManager = LinearLayoutManager(requireContext())
            rvOptions.adapter = OptionsAdapter(options) {
                onOptionClicked.invoke(it)
                dismiss()
            }
        }
    }
}