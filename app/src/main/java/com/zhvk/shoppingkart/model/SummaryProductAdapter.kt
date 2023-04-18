package com.zhvk.shoppingkart.model

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.databinding.ItemProductSummaryBinding

private const val TAG = "SummaryProductAdapter"

/**
 * Adapter for Products which are shown on the SummaryFragment
 */
class SummaryProductAdapter(
    private val viewModel: CartViewModel,
    private var cartItems: List<CartItem> = viewModel.cartItems.value?.toList() ?: emptyList(),
    private val cartItemListener: CartItemListener = viewModel
) : RecyclerView.Adapter<SummaryProductAdapter.ProductViewHolder>() {

    private lateinit var binding: ItemProductSummaryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = ItemProductSummaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem, viewModel)
        Log.d(TAG, "onBindViewHolder: Product: ${cartItem.product.name}(ID ${cartItem.product.id}), quantity: ${cartItem.quantity}")
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun updateData(list: List<CartItem>?) {
        cartItems = list ?: emptyList()
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val binding: ItemProductSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem, viewModel: CartViewModel) {
            binding.cartItem = cartItem
            binding.listener = viewModel
        }
    }
}