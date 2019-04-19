package com.ulj.slicky.kotlinfakesocial.activity.friends

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ulj.slicky.kotlinfakesocial.R
import com.ulj.slicky.kotlinfakesocial.activity.profile.ProfileActivity.Companion.startFriendProfile
import com.ulj.slicky.kotlinfakesocial.fullName
import com.ulj.slicky.kotlinfakesocial.info
import com.ulj.slicky.kotlinfakesocial.model.person.Person
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

/**
 * Created by SlickyPC on 24.5.2017
 */
internal class FriendsAdapter(
        private val activity: FriendsActivity,
        private val recycler: RecyclerView
) : RecyclerView.Adapter<FriendsViewHolder>() {

    internal var friends = listOf<Person>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.friends_item, parent, false)

        view.setOnClickListener {
            val itemPosition = recycler.getChildLayoutPosition(view)
            val friend = friends[itemPosition]
            activity.startFriendProfile(friend)
        }

        return FriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val friend = friends[position]

        with(holder) {
            friendName.text = friend.fullName()
            friendInfo.text = friend.info()

            Picasso.with(activity)
                    .load(friend.picture.medium)
                    .placeholder(R.drawable.ic_user)
                    .transform(CropCircleTransformation())
                    .into(friendImage)
        }
    }

    override fun getItemCount() = friends.size

}
