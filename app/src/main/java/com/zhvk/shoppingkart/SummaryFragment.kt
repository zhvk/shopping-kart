package com.zhvk.shoppingkart

import android.content.DialogInterface
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
            adapter = SummaryProductAdapter(sharedViewModel)

            recyclerView.setHasFixedSize(true)
        }


        // TODO: Find a better solution
//        val nameObserver = Observer<MutableList<CartItem>> { newMutableList ->
//            summaryAdapter.updateData(newMutableList.toList())
//            Log.d(TAG, "321")
//        }
//        sharedViewModel.cartItems.observe(viewLifecycleOwner, nameObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // TODO
    fun showLocationPickerDialog() {
        val dialog = LocationDialog()
        dialog.show(parentFragmentManager, "locationFragmentDialog")
    }

    // TODO
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
}