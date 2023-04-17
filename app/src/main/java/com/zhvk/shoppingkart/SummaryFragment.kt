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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.zhvk.shoppingkart.data.DataSource
import com.zhvk.shoppingkart.databinding.FragmentSummaryBinding
import com.zhvk.shoppingkart.model.BrowseProductAdapter
import com.zhvk.shoppingkart.model.CartViewModel

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

//            recyclerView.adapter = BrowseSummaryAdapter()
            recyclerView.layoutManager = LinearLayoutManager(context)
            // Specify fixed size to improve performance
            recyclerView.setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showLocationPickerDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select new location")
        builder.setMessage("Select new location")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(R.string.change_location) { dialog: DialogInterface, which: Int ->
            Toast.makeText(requireContext(),
                android.R.string.yes, Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton(R.string.cancel) { dialog: DialogInterface, which: Int ->
            Toast.makeText(requireContext(),
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    fun showPaymentPickerDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select preferred way of payment")
        builder.setMessage("Select preferred way of payment")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(R.string.change_location) { dialog: DialogInterface, which: Int ->
            Toast.makeText(requireContext(),
                android.R.string.yes, Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton(R.string.cancel) { dialog: DialogInterface, which: Int ->
            Toast.makeText(requireContext(),
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}