package com.m7.shifttracker.addShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.m7.shifttracker.Shift
import com.m7.shifttracker.databinding.FragmentAddShiftBinding
import java.text.SimpleDateFormat
import java.util.*

class AddShiftFragment(private val onSaveCLicked: (shift: Shift) -> Unit) : Fragment() {

    private lateinit var binding: FragmentAddShiftBinding

    private val datePicker = MaterialDatePicker.Builder.datePicker().build()
    private var startOrEndDate = 1
    private val stringToDateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    private val dateToStringFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddShiftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            // start date picker
            val onStartDateCLickListener = View.OnClickListener {
                if (!datePicker.isAdded) {
                    startOrEndDate = 1
                    datePicker.show(childFragmentManager, datePicker.tag)
                }
            }
            etStartDate.setOnClickListener(onStartDateCLickListener)
            btnStartDateCalender.setOnClickListener(onStartDateCLickListener)

            // end date picker
            val onEndDateCLickListener = View.OnClickListener {
                if (!datePicker.isAdded) {
                    startOrEndDate = 2
                    datePicker.show(childFragmentManager, datePicker.tag)
                }
            }
            etEndDate.setOnClickListener(onEndDateCLickListener)
            btnEndDateCalender.setOnClickListener(onEndDateCLickListener)

            datePicker.addOnPositiveButtonClickListener {
                if (startOrEndDate == 1) {
                    etStartDate.setText(datePicker.headerText)
                } else {
                    etEndDate.setText(datePicker.headerText)
                }
            }

            // name options
            etName.setOnClickListener {
                val optionsBottomSheet =
                    OptionsBottomSheet(listOf("Anna", "Anton", "Eugene", "Keyvan")) {
                        etName.setText(it)
                    }

                if (!optionsBottomSheet.isAdded) {
                    optionsBottomSheet.show(childFragmentManager, optionsBottomSheet.tag)
                }
            }

            // role options
            etRole.setOnClickListener {
                val optionsBottomSheet =
                    OptionsBottomSheet(listOf("Waiter", "Prep", "Front of House", "Cook")) {
                        etRole.setText(it)
                    }

                if (!optionsBottomSheet.isAdded) {
                    optionsBottomSheet.show(childFragmentManager, optionsBottomSheet.tag)
                }
            }

            // color options
            etColor.setOnClickListener {
                val optionsBottomSheet = OptionsBottomSheet(listOf("red", "green", "blue")) {
                    etColor.setText(it)
                }

                if (!optionsBottomSheet.isAdded) {
                    optionsBottomSheet.show(childFragmentManager, optionsBottomSheet.tag)
                }
            }

            // save
            btnSave.setOnClickListener {
                if (checkEntities()) {
                    onSaveCLicked.invoke(
                        Shift(
                            etRole.text.toString(),
                            etName.text.toString(),
                            formatDate(etStartDate.text.toString()),
                            formatDate(etEndDate.text.toString()),
                            etColor.text.toString()
                        )
                    )

                    requireActivity().onBackPressed()
                }
            }

            // back
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun checkEntities(): Boolean {
        var valid = true

        binding.apply {
            if (etStartDate.text.isNullOrBlank()) {
                etStartDate.error = "invalid"
                valid = false
            }
            if (etEndDate.text.isNullOrBlank()) {
                etEndDate.error = "invalid"
                valid = false
            }
            if (etName.text.isNullOrBlank()) {
                etName.error = "invalid"
                valid = false
            }
            if (etRole.text.isNullOrBlank()) {
                etRole.error = "invalid"
                valid = false
            }
            if (etColor.text.isNullOrBlank()) {
                etColor.error = "invalid"
                valid = false
            }
        }

        return valid
    }

    private fun formatDate(dateString: String): String {
        return dateToStringFormatter.format(stringToDateFormatter.parse(dateString))
    }
}