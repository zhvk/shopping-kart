package com.zhvk.shoppingkart.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhvk.shoppingkart.R
import java.text.NumberFormat

private const val TAG = "SummaryProductAdapter"

/**
 * Adapter for Products which are shown on the SummaryFragment
 */
class SummaryProductAdapter(
    private val viewModel: CartViewModel,
) : RecyclerView.Adapter<SummaryProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_product_summary, parent, false)
        return ProductViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = viewModel.cartItems.value?.get(position) ?: CartItem()
        holder.imageView.setImageResource(item.product.imageResourceIds[0])
        holder.nameView.text = item.product.name
        holder.typeView.text = item.product.type
        holder.priceView.text = NumberFormat.getCurrencyInstance().format(item.product.price)
        holder.quantityView.text = item.quantity.toString()

        holder.reduceQuantityButton.setOnClickListener {
            viewModel.reduceItemQuantity(item)
            notifyItemChanged(position)
        }
        holder.increaseQuantityButton.setOnClickListener {
            viewModel.addItem(item)
            notifyItemChanged(position)
        }
        holder.deleteItemButton.setOnClickListener {
            viewModel.removeItemFromCart(item)
            notifyItemRemoved(position)
            notifyItemChanged(position) // Hack
        }
    }

    override fun getItemCount(): Int {
        return viewModel.cartItems.value?.size ?: 0
    }

    class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val nameView: TextView = view.findViewById(R.id.item_name)
        val typeView: TextView = view.findViewById(R.id.item_type)
        val priceView: TextView = view.findViewById(R.id.item_price)
        val quantityView: TextView = view.findViewById(R.id.item_quantity)

        val reduceQuantityButton: ImageButton = view.findViewById(R.id.item_button_reduce_quantity)
        val increaseQuantityButton: ImageButton = view.findViewById(R.id.item_button_increase_quantity)
        val deleteItemButton: ImageButton = view.findViewById(R.id.item_button_remove_product)
    }
}