package com.ulj.slicky.kotlinfakesocial.activity.content

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ulj.slicky.kotlinfakesocial.R

/**
 * Created by SlickyPC on 19.5.2017
 */
internal class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ownerImage: ImageView = itemView.findViewById<ImageView>(R.id.content_owner_image)
    val ownerName: TextView = itemView.findViewById<TextView>(R.id.content_owner_name)
    val postedAt: TextView = itemView.findViewById<TextView>(R.id.content_posted_at)
    val content: TextView = itemView.findViewById<TextView>(R.id.content_content)
}
