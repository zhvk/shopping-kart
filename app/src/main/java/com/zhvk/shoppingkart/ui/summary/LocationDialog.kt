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
import com.zhvk.shoppingkart.ui.CartViewModel

// TODO
class LocationDialog : DialogFragment() {

    private val sharedViewModel: CartViewModel by activityViewModels()

    private var _binding: DialogLocationEntryBinding? = null
    private val binding get() = _binding!!

    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_location_entry, container, false)
        return binding.root
    }*/

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

        return builder.create();
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun setAddress() {
        /*binding.viewModel?.setAddress(
                UserAddress(
                    view?.findViewById<TextInputLayout>(R.id.location_street_name)?.editText?.text.toString(),
                    view?.findViewById<TextInputLayout>(R.id.location_street_number)?.editText?.text.toString(),
                    view?.findViewById<TextInputLayout>(R.id.location_postal_code)?.editText?.text.toString(),
                    view?.findViewById<TextInputLayout>(R.id.location_city)?.editText?.text.toString()
                )
            )*/
    }
}