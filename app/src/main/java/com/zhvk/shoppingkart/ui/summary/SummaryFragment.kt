package com.zhvk.shoppingkart.ui.summary

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.FragmentSummaryBinding
import com.zhvk.shoppingkart.ui.CartViewModel

/**
 * Fragment displaying order summary and checkout.
 */
class SummaryFragment : Fragment() {

    private val sharedViewModel: CartViewModel by activityViewModels()

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    // TODO: This ActivityResultLauncher is used only for demonstration purposes and  should be removed
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            navigateToSuccessFragment()
        }

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

            actionCancelOrder.setOnClickListener { showCancelOrderDialog() }

            sharedViewModel.address.observe(viewLifecycleOwner) { address ->
                deliveryLocationAddressNotSetLabel.visibility =
                    if (address.isSet()) View.GONE else View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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
                R.string.yes, Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNegativeButton(R.string.cancel) { dialog: DialogInterface, which: Int ->
            Toast.makeText(
                requireContext(),
                R.string.no, Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }

    fun attemptToBuy() {
        if (sharedViewModel.address.value?.isSet() == false) {
            Toast.makeText(requireContext(), "Please fill your address", Toast.LENGTH_LONG).show()
            return
        }

        val cartItems = sharedViewModel.cartItems.value
        if (cartItems == null || cartItems.size < 1) {
            Toast.makeText(requireContext(), "Your cart is empty", Toast.LENGTH_LONG).show()
            return
        }

        sendOrderViaEmail()
    }

    // Method that sends order details via email.
    fun sendOrderViaEmail(): Boolean {
        val emailBody = getString(
            R.string.order_email_body,
            sharedViewModel.getOrderString(),
            sharedViewModel.address.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("orders@soundhaven.shop")) // recipients
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_details))
            putExtra(Intent.EXTRA_TEXT, emailBody)
        }

        startForResult.launch(intent)

        return true
    }

    fun navigateToSuccessFragment() {
        val action = SummaryFragmentDirections.actionSummaryFragmentToSuccessFragment()
        findNavController().navigate(action)

        // TODO: Since we don't have proper implementation of sending order via API, we are
        //  canceling the order because we want to clear shared ViewModel data.
        sharedViewModel.cancelOrder()
    }

    private fun cancelOrder() {
        Toast.makeText(context, R.string.cancel_order_confirmation, Toast.LENGTH_LONG).show()
        requireActivity().onBackPressedDispatcher.onBackPressed()
        sharedViewModel.cancelOrder()
    }

    private fun showCancelOrderDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(R.string.cancel_order_dialog_title)
            .setCancelable(false)
            .setPositiveButton(R.string.yes) { dialog, id ->
                cancelOrder()
            }
            .setNegativeButton(R.string.no) { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}