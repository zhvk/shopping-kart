package com.zhvk.shoppingkart

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zhvk.shoppingkart.data.DataSource
import com.zhvk.shoppingkart.databinding.FragmentProductBinding
import com.zhvk.shoppingkart.model.CartItem
import com.zhvk.shoppingkart.model.CartViewModel
import com.zhvk.shoppingkart.model.Product
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

    private var productId: Int = -1
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            productId = it.getInt(PRODUCT_ID)
        }

        product = DataSource.products.firstOrNull { it.id == productId }
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

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@ProductFragment

            // TODO: Handle case when product can be null
            productImage.setImageResource(product?.imageResourceId ?: R.drawable.headphones1)
            productTitle.text = product?.name
            productType.text = product?.type
            productDescription.text = product?.description
            productType.text = product?.type
            productPriceValue.text = NumberFormat.getCurrencyInstance().format(product?.price)
            if (product?.isAvailable == false) productOutOfStockInfo.visibility = View.GONE

            buttonProductUrl.setOnClickListener { searchFor(product?.name) }
            buttonAddToCart.setOnClickListener { sharedViewModel.addItem(CartItem(product!!, 1)) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchFor(parameter: String?) {
        val queryUrl: Uri = Uri.parse("${SEARCH_PREFIX}${parameter}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        context?.startActivity(intent)
    }
}