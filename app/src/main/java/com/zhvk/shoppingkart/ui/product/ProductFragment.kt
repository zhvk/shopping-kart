package com.zhvk.shoppingkart.ui.product

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.FragmentProductBinding
import com.zhvk.shoppingkart.model.CartItem
import com.zhvk.shoppingkart.model.Product
import com.zhvk.shoppingkart.ui.CartViewModel
import java.text.NumberFormat

const val PRODUCT_ID = "productId"
const val SEARCH_PREFIX = "https://www.google.com/search?q="

/**
 * Fragment displaying a Product info and allows users to add a Product to Cart
 */
class ProductFragment : Fragment() {

    private val sharedViewModel: CartViewModel by activityViewModels()

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private var productId: Long = -1
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            productId = it.getLong(PRODUCT_ID)
        }
        product = sharedViewModel.getProduct(productId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val menuHost: MenuHost = requireActivity()
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
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)*/

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@ProductFragment

            val viewPagerAdapter = ViewPagerAdapter(product?.imageResourceIds ?: emptyList())
            productImageViewPager.adapter = viewPagerAdapter
            if (viewPagerAdapter.itemCount > 1) dotsIndicator.attachTo(productImageViewPager)

            productTitle.text = product?.name
            productType.text = product?.type
            productDescription.text = product?.description
            productType.text = product?.type
            productPriceValue.text = NumberFormat.getCurrencyInstance().format(product?.price)
            if (product?.isAvailable == true) {
                productOutOfStockInfo.visibility = View.GONE
                buttonAddToCart.isEnabled = true
            }
            setFavouriteIcon(product)

//            buttonProductUrl.setOnClickListener { searchFor(product?.name) }
            buttonAddToCart.setOnClickListener {
                sharedViewModel.addItem(CartItem(product!!, 1))
                showSnackbar()
            }
            buttonFavourite.setOnClickListener {
                product?.switchFavourite()
                setFavouriteIcon(product)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchFor(parameter: String?) {
        val queryUrl: Uri = Uri.parse("$SEARCH_PREFIX${parameter}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        context?.startActivity(intent)
    }

    private fun showSnackbar() {
        Snackbar.make(requireView(), "Product added to Cart", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                sharedViewModel.reduceItemQuantity(CartItem(product!!, 1))
            }
            .show()
    }

    private fun navigateToCheckout() {
        val action = ProductFragmentDirections.actionProductFragmentToSummaryFragment()
        findNavController().navigate(action)
    }

    private fun setFavouriteIcon(product: Product?) {
        val drawable = if (product?.isFavourite == true) R.drawable.round_favourite_filled
        else R.drawable.round_favourite_border
        binding.buttonFavourite.setImageResource(drawable)
    }
}