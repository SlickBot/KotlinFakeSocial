package com.ulj.slicky.kotlinfakesocial.activity.friends

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ulj.slicky.kotlinfakesocial.R

/**
 * Created by SlickyPC on 24.5.2017
 */
internal class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val friendImage: ImageView = itemView.findViewById<ImageView>(R.id.friend_image)
    val friendName: TextView = itemView.findViewById<TextView>(R.id.friend_name)
    val friendInfo: TextView = itemView.findViewById<TextView>(R.id.friend_info)
}
