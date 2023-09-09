package com.zhvk.shoppingkart.ui.browse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.ItemFilterBinding
import com.zhvk.shoppingkart.model.Filter

class FilterAdapter(
    private val onItemClicked: (Filter) -> Unit
) : ListAdapter<Filter, FilterAdapter.FilterViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Filter>() {
        override fun areItemsTheSame(oldItem: Filter, newItem: Filter): Boolean {
//            return oldItem.id == newItem.id
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return (oldItem.name == newItem.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            ItemFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }

    fun selectFilter(filter: Filter) {
        notifyItemChanged(currentList.indexOf(filter))
    }

    inner class FilterViewHolder(
        private var binding: ItemFilterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(filter: Filter, onItemClicked: (Filter) -> Unit) {
            binding.filterLabel.text = filter.name
            binding.root.setOnClickListener { onItemClicked.invoke(filter) }

            val bkg = if (filter.isSelected) R.drawable.background_filter_selected
            else R.drawable.background_filter
            binding.filterView.setBackgroundResource(bkg)
        }
    }
}