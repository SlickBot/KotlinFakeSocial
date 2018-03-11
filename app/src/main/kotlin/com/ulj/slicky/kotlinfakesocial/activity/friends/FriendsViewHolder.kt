package com.ulj.slicky.kotlinfakesocial.activity.friends

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.friends_item.view.*

/**
 * Created by SlickyPC on 24.5.2017
 */
internal class FriendsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val friendImage: ImageView = view.friend_image
    val friendName: TextView = view.friend_name
    val friendInfo: TextView = view.friend_info
}
