package com.zhvk.shoppingkart.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.ItemProductBrowseBinding
import com.zhvk.shoppingkart.model.Product

/**
 * Adapter for Products which are shown on the BrowseFragment and on the FavouritesFragment
 */
class BrowseProductsAdapter(
    private val clickListener: BrowseProductClickListener
) : ListAdapter<Product, BrowseProductsAdapter.BrowseProductViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return (oldItem.name == newItem.name &&
                    oldItem.type == newItem.type &&
                    oldItem.description == newItem.description &&
                    oldItem.price == newItem.price &&
                    oldItem.isAvailable == newItem.isAvailable &&
                    oldItem.imageResourceIds == newItem.imageResourceIds)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseProductViewHolder {
        // TODO: Which is proper way of inflating VH when using Data Binding?
//        return BrowseProductViewHolder(ItemProductBrowseBinding.inflate(LayoutInflater.from(parent.context)))
        val binding: ItemProductBrowseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product_browse,
            parent,
            false
        )
        return BrowseProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrowseProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class BrowseProductViewHolder(
        private var binding: ItemProductBrowseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, clickListener: BrowseProductClickListener) {
            binding.product = product
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class BrowseProductClickListener(val clickListener: (productId: Long) -> Unit) {
    fun onClick(product: Product) = clickListener(product.id)
}