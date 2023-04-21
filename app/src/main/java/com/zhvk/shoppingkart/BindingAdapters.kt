package com.zhvk.shoppingkart

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.model.BrowseProductAdapter
import com.zhvk.shoppingkart.model.Product

@BindingAdapter("observeData")
fun bindBrowseRecyclerView(recyclerView: RecyclerView, data: List<Product>?) {
    val adapter = recyclerView.adapter as BrowseProductAdapter
    adapter.submitList(data)
}

/*@BindingAdapter("observeData")
fun bindSummaryRecyclerView(recyclerView: RecyclerView, data: MutableList<CartItem>?) {
    val adapter = recyclerView.adapter as SummaryProductAdapter
    adapter.submitList(data)
}*/

// "Hacky" way of setting image resource with current DataSource. You don't need this with real repository
@BindingAdapter("setImageResource")
fun setImageResource(imageView: ImageView, imageResource: Int) {
    imageView.setImageResource(imageResource)
}
