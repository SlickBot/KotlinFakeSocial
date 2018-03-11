package com.ulj.slicky.kotlinfakesocial.activity.content

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.content_item.view.*

/**
 * Created by SlickyPC on 19.5.2017
 */
internal class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ownerImage: ImageView = view.content_owner_image
    val ownerName: TextView = view.content_owner_name
    val postedAt: TextView = view.content_posted_at
    val content: TextView = view.content_content
}
