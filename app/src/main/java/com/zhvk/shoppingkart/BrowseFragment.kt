package com.zhvk.shoppingkart

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.zhvk.shoppingkart.data.DataSource
import com.zhvk.shoppingkart.databinding.FragmentBrowseBinding
import com.zhvk.shoppingkart.model.BrowseProductAdapter
import com.zhvk.shoppingkart.model.CartViewModel

/**
 * Fragment for browsing all Store Products. This is the first screen on which the user lands.
 */
class BrowseFragment : Fragment() {

    // TODO: This is not used at the moment in the BrowseFragment
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

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.layout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_go_to_checkout -> {
                        navigateToCheckout()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@BrowseFragment

            productsHeaderNumber.text = DataSource.products.size.toString()

            recyclerView.adapter = BrowseProductAdapter()
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            // Specify fixed size to improve performance
            recyclerView.setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToCheckout() {
        val action = BrowseFragmentDirections.actionBrowseFragmentToSummaryFragment()
        findNavController().navigate(action)
    }
}