package com.zhvk.shoppingkart

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zhvk.shoppingkart.databinding.FragmentSummaryBinding
import com.zhvk.shoppingkart.model.CartViewModel
import com.zhvk.shoppingkart.model.SummaryProductAdapter

private const val TAG = "SummaryFragment"

/**
 * Fragment displaying order summary and checkout.
 */
class SummaryFragment : Fragment() {

    private val sharedViewModel: CartViewModel by activityViewModels()

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@SummaryFragment

            recyclerView.adapter = SummaryProductAdapter(sharedViewModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // TODO: Implement picking a location
    fun showLocationPickerDialog() {
        val dialog = LocationDialog()
        dialog.show(parentFragmentManager, "locationFragmentDialog")
    }

    // TODO: Implement
    fun showPaymentPickerDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select preferred way of payment")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(R.string.change_location) { dialog: DialogInterface, which: Int ->
            Toast.makeText(
                requireContext(),
                android.R.string.yes, Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNegativeButton(R.string.cancel) { dialog: DialogInterface, which: Int ->
            Toast.makeText(
                requireContext(),
                android.R.string.no, Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }

    fun sendOrder() {
        val emailBody = getString(
            R.string.order_email_body,
            sharedViewModel.getOrderString(),
            sharedViewModel.address.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("soundhaven@example.com")) // recipients
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_details))
            putExtra(Intent.EXTRA_TEXT, emailBody)
        }
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }
}