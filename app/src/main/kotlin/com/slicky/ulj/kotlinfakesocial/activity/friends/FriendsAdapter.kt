package com.slicky.ulj.kotlinfakesocial.activity.friends

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.profile.ProfileActivity.Companion.startFriendProfile
import com.slicky.ulj.kotlinfakesocial.fullName
import com.slicky.ulj.kotlinfakesocial.info
import com.slicky.ulj.kotlinfakesocial.model.person.Person
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

/**
 * Created by SlickyPC on 24.5.2017
 */
internal class FriendsAdapter(private val activity: FriendsActivity,
                              private val recycler: RecyclerView)
    : RecyclerView.Adapter<FriendsViewHolder>() {

    private val friendsList = mutableListOf<Person>()

    fun setFriends(friends: List<Person>?) {
        friendsList.clear()
        friends?.let { friendsList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.friends_item, parent, false)

        view.setOnClickListener {
            val itemPosition = recycler.getChildLayoutPosition(view)
            val friend = friendsList[itemPosition]
            activity.startFriendProfile(friend)
        }

        return FriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val friend = friendsList[position]
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

    override fun getItemCount() = friendsList.size
}
