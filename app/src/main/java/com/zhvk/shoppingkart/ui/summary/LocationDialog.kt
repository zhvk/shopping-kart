package com.zhvk.shoppingkart.ui.summary

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.DialogLocationEntryBinding
import com.zhvk.shoppingkart.model.UserAddress
import com.zhvk.shoppingkart.ui.CartViewModel

class LocationDialog : DialogFragment() {

    private val sharedViewModel: CartViewModel by activityViewModels()

    private var _binding: DialogLocationEntryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.dialog_location_entry, null, false)

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter new location")
        builder.setView(binding.root)
        builder.setPositiveButton(R.string.change_location) { dialog: DialogInterface, which: Int ->
            setAddress()
        }
        builder.setNegativeButton(R.string.cancel) { dialog: DialogInterface, which: Int ->
            dialog.cancel()
        }

        return builder.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAddress() {
        with(binding) {
            val address = UserAddress(
                streetName = locationStreetName.editText?.text.toString(),
                streetNumber = locationStreetNumber.editText?.text.toString(),
                postalCode = locationPostalCode.editText?.text.toString(),
                city = locationCity.editText?.text.toString(),
            )
            sharedViewModel.setAddress(address)
        }
    }
}