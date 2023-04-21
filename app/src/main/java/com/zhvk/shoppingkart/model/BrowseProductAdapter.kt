package com.zhvk.shoppingkart.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.data.DataSource
import com.zhvk.shoppingkart.databinding.ItemProductBrowseBinding

/**
 * Adapter for Products which are shown on the BrowseFragment
 */
class BrowseProductAdapter(private val clickListener: BrowseProductClickListener) :
    ListAdapter<Product, BrowseProductAdapter.BrowseProductViewHolder>(DiffCallback) {

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
        return BrowseProductViewHolder(ItemProductBrowseBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BrowseProductViewHolder, position: Int) {
        val item = DataSource.products[position]
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

class BrowseProductClickListener(val clickListener: (productId: Int) -> Unit) {
    fun onClick(product: Product) = clickListener(product.id)
}