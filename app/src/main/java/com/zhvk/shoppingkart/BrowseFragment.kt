package com.zhvk.shoppingkart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.zhvk.shoppingkart.data.DataSource
import com.zhvk.shoppingkart.databinding.FragmentBrowseBinding
import com.zhvk.shoppingkart.model.BrowseProductAdapter
import com.zhvk.shoppingkart.model.CartViewModel


/**
 * Fragment for browsing all Store Products. This is the first screen on which the user lands.
 */
class BrowseFragment : Fragment() {

    private val sharedViewModel: CartViewModel by activityViewModels()

    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_browse, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@BrowseFragment

            productsHeaderNumber.text = DataSource.products.size.toString()

            recyclerView.adapter = BrowseProductAdapter()
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateToCheckout() {
        val action = BrowseFragmentDirections.actionBrowseFragmentToSummaryFragment()
        findNavController().navigate(action)
    }
}