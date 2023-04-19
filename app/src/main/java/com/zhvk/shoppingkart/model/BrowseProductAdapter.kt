package com.zhvk.shoppingkart.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.BrowseFragmentDirections
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.data.DataSource
import java.text.NumberFormat

/**
 * Adapter for Products which are shown on the BrowseFragment
 */
class BrowseProductAdapter : RecyclerView.Adapter<BrowseProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_product_browse, parent, false)
        return ProductViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = DataSource.products[position]
        holder.imageView.setImageResource(item.imageResourceIds[0])
        holder.nameView.text = item.name
        holder.typeView.text = item.type
        holder.priceView.text = NumberFormat.getCurrencyInstance().format(item.price)
        holder.availabilityView.visibility = if (item.isAvailable) View.GONE else View.VISIBLE

        holder.view.setOnClickListener {
            val action = BrowseFragmentDirections.actionBrowseFragmentToProductFragment(
                productId = item.id
            )
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return DataSource.products.size
    }

    class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val nameView: TextView = view.findViewById(R.id.item_name)
        val typeView: TextView = view.findViewById(R.id.item_type)
        val priceView: TextView = view.findViewById(R.id.item_price)
        val availabilityView: TextView = view.findViewById(R.id.item_availability)
    }
}