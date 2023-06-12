package com.zhvk.shoppingkart.ui.tutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.model.TutorialPageInfo

class TutorialViewPagerAdapter(private val data : List<TutorialPageInfo>) :
    RecyclerView.Adapter<TutorialViewPagerAdapter.TutorialPageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialPageViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tutorial_page, parent, false)
        return TutorialPageViewHolder(layout)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TutorialPageViewHolder, position: Int) {
        val item = data[position]
        holder.animationView.setAnimation(item.animationResource)
        holder.titleView.text = item.title
        holder.descriptionView.text = item.description
    }

    class TutorialPageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val animationView: LottieAnimationView = view.findViewById(R.id.animation_view)
        val titleView: TextView = view.findViewById(R.id.tutorial_page_title)
        val descriptionView: TextView = view.findViewById(R.id.tutorial_page_description)
    }
}