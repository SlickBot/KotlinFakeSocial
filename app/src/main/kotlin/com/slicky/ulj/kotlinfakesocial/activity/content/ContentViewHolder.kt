package com.slicky.ulj.kotlinfakesocial.activity.content

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.slicky.ulj.kotlinfakesocial.R

/**
 * Created by SlickyPC on 19.5.2017
 */
internal class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ownerImage = itemView.findViewById(R.id.owner_image) as ImageView
    val ownerName = itemView.findViewById(R.id.owner_name) as TextView
    val postedAt = itemView.findViewById(R.id.posted_at) as TextView
    val content = itemView.findViewById(R.id.content) as TextView
}
