package com.zhvk.shoppingkart.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.R

class ProductImagesViewPagerAdapter(private val imageList: List<Int>) :
    RecyclerView.Adapter<ProductImagesViewPagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image_slider, parent, false)
        return ImageViewHolder(layout)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imageList[position]
        holder.imageView.setImageResource(item)
    }

    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.product_image)
    }
}