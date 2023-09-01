package com.zhvk.shoppingkart.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.model.Product
import com.zhvk.shoppingkart.ui.BrowseProductsAdapter

@BindingAdapter("observeData")
fun bindBrowseRecyclerView(recyclerView: RecyclerView, data: List<Product>?) {
    val adapter = recyclerView.adapter as BrowseProductsAdapter
    adapter.submitList(data)
}

//@BindingAdapter("observeData")
//fun bindBrowseRecyclerView(recyclerView: RecyclerView, data: Set<Filter>?) {
//    val adapter = recyclerView.adapter as FilterAdapter
//    adapter.submitList(data?.toList())
//}

/*@BindingAdapter("isFavourite")
fun setFavourite(imageButton: ImageButton, isFavourite: Boolean) {
    val drawable = if (isFavourite) R.drawable.round_favorite_filled
    else R.drawable.round_favorite_border
    imageButton.setImageResource(drawable)
}*/

// TODO: "Hacky" way of setting image resource with current DataSource. You don't need this with real repository
@BindingAdapter("setImageResource")
fun setImageResource(imageView: ImageView, imageResource: Int) {
    imageView.setImageResource(imageResource)
}
