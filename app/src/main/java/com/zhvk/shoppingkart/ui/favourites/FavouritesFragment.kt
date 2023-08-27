package com.zhvk.shoppingkart.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.FragmentFavouritesBinding
import com.zhvk.shoppingkart.model.Product
import com.zhvk.shoppingkart.ui.BrowseProductClickListener
import com.zhvk.shoppingkart.ui.BrowseProductsAdapter
import com.zhvk.shoppingkart.ui.CartViewModel

class FavouritesFragment : Fragment() {

    private val sharedViewModel: CartViewModel by activityViewModels()

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favouritesAdapter: BrowseProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favouritesAdapter = BrowseProductsAdapter(BrowseProductClickListener { productId ->
            val action =
                FavouritesFragmentDirections.actionFavouritesFragmentToProductFragment(
                    productId = productId
                )
            findNavController().navigate(action)
        })
        favouritesAdapter.submitList(sharedViewModel.getFavourites())

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel

            recyclerView.adapter = favouritesAdapter
            recyclerView.setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}