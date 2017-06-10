package com.slicky.ulj.kotlinfakesocial.activity.content

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.slicky.ulj.kotlinfakesocial.R
import com.slicky.ulj.kotlinfakesocial.activity.detail.DetailActivity.Companion.startDetail
import com.slicky.ulj.kotlinfakesocial.formattedWithTime
import com.slicky.ulj.kotlinfakesocial.fullName
import com.slicky.ulj.kotlinfakesocial.model.content.Content
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.util.*

/**
 * Created by SlickyPC on 19.5.2017
 */
internal class ContentAdapter(private val activity: ContentActivity,
                              private val recycler: RecyclerView)
    : RecyclerView.Adapter<ContentViewHolder>() {

    internal var contents = listOf<Content>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_item, parent, false)

        view.setOnClickListener {
            val itemPosition = recycler.getChildLayoutPosition(view)
            val newContent = contents[itemPosition]
            activity.startDetail(newContent)
        }

        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val newContent = contents[position]
        val owner = newContent.owner
        val date = Date(newContent.postedAt)

        with(holder) {
            ownerName.text = owner.fullName()
            postedAt.text = date.formattedWithTime()
            content.text = newContent.text

            Picasso.with(activity)
                    .load(owner.picture.medium)
                    .placeholder(R.drawable.ic_user)
                    .transform(CropCircleTransformation())
                    .into(ownerImage)
        }
    }

    override fun getItemCount() = contents.size
}
