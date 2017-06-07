package com.slicky.ulj.kotlinfakesocial.activity.friends

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.slicky.ulj.kotlinfakesocial.R

/**
 * Created by SlickyPC on 24.5.2017
 */
internal class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val friendImage = itemView.findViewById(R.id.friend_image) as ImageView
    val friendName = itemView.findViewById(R.id.friend_name) as TextView
    val friendInfo = itemView.findViewById(R.id.friend_info) as TextView
}
