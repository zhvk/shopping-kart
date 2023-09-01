package com.zhvk.shoppingkart.ui.browse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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

    private lateinit var productsAdapter: BrowseProductsAdapter
    private lateinit var filterAdapter: FilterAdapter

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

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val tutorialFinished =
            sharedPref?.getBoolean(getString(R.string.tutorial_preference_key), false)

        if (tutorialFinished == false) navigateToTutorial()

        productsAdapter = BrowseProductsAdapter(BrowseProductClickListener { productId ->
            val action = BrowseFragmentDirections.actionBrowseFragmentToProductFragment(
                productId = productId
            )
            findNavController().navigate(action)
        })
        filterAdapter = FilterAdapter {
            sharedViewModel.selectFilter(it)
            filterAdapter.notifyDataSetChanged()
        }
        filterAdapter.submitList(sharedViewModel.filters.value?.toList())

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@BrowseFragment

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(searchString: String): Boolean {
                    sharedViewModel.searchProducts(searchString)
                    return false
                }
            })


            filterRecycler.adapter = filterAdapter
            recyclerView.adapter = productsAdapter
            // TODO: Should be false with the new features
            recyclerView.setHasFixedSize(false)

            goToFavouritesButton.setOnClickListener { navigateToFavouritesFragment() }
        }

        observeBrowseData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun observeBrowseData() {
        sharedViewModel.browseData.observe(viewLifecycleOwner) { browseProducts ->
            productsAdapter.submitList(browseProducts)
            handleSearchResultsUi(browseProducts.size != 0)
        }
    }

    fun handleSearchResultsUi(hasResults: Boolean) {
        binding.apply {
            if (hasResults) {
                recyclerView.visibility = View.VISIBLE
                noSearchResultsView.visibility = View.GONE
            } else {
                recyclerView.visibility = View.GONE
                noSearchResultsView.visibility = View.VISIBLE
            }
        }
    }

    fun navigateToCheckout() {
        val action = BrowseFragmentDirections.actionBrowseFragmentToSummaryFragment()
        findNavController().navigate(action)
    }

    fun navigateToFavouritesFragment() {
        val action = BrowseFragmentDirections.actionBrowseFragmentToFavouritesFragment()
        findNavController().navigate(action)
    }

    fun navigateToTutorial() {
        val action = BrowseFragmentDirections.actionBrowseFragmentToTutorialFragment()
        findNavController().navigate(action)
    }
}