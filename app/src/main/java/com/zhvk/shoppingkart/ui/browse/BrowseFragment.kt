package com.zhvk.shoppingkart.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.FragmentBrowseBinding
import com.zhvk.shoppingkart.ui.BrowseProductClickListener
import com.zhvk.shoppingkart.ui.BrowseProductsAdapter
import com.zhvk.shoppingkart.ui.CartViewModel

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

            recyclerView.adapter = BrowseProductsAdapter(sharedViewModel.getBrowseData(),
                BrowseProductClickListener { productId ->
                    val action = BrowseFragmentDirections.actionBrowseFragmentToProductFragment(
                        productId = productId
                    )
                    findNavController().navigate(action)
                })
            // TODO: Should be false with the new features
            recyclerView.setHasFixedSize(false)

            goToFavouritesButton.setOnClickListener { navigateToFavouritesFragment() }
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

    fun navigateToFavouritesFragment() {
        val action = BrowseFragmentDirections.actionBrowseFragmentToFavouritesFragment()
        findNavController().navigate(action)
    }
}