package com.slicky.ulj.kotlinfakesocial.activity.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.slicky.ulj.kotlinfakesocial.*
import com.slicky.ulj.kotlinfakesocial.activity.BackableActivity
import com.slicky.ulj.kotlinfakesocial.model.person.Person
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

/**
 * Created by SlickyPC on 22.5.2017
 */
class ProfileActivity : BackableActivity() {
    companion object {
        private val TAG = ProfileActivity::class.java.canonicalName
        private val KEY_PERSON = TAG + ".person"
        private val KEY_OWNER = TAG + ".owner"

        fun getOwnerIntent(context: Context, owner: Person): Intent {
            return Intent(context, ProfileActivity::class.java).apply {
                putExtra(KEY_PERSON, owner)
                putExtra(KEY_OWNER, true)
            }
        }

        fun getFriendIntent(context: Context, friend: Person): Intent {
            return Intent(context, ProfileActivity::class.java).apply {
                putExtra(KEY_PERSON, friend)
                putExtra(KEY_OWNER, false)
            }
        }
    }

    private val imageView by findView<ImageView>(R.id.profile_icon)
    private val nameField by findView<TextView>(R.id.profile_name)
    private val emailField by findView<TextView>(R.id.profile_email)
    private val cellField by findView<TextView>(R.id.profile_cell)
    private val phoneField by findView<TextView>(R.id.profile_phone)
    private val birthdayField by findView<TextView>(R.id.profile_birthday)
    private val registeredField by findView<TextView>(R.id.profile_registered)
    private val streetField by findView<TextView>(R.id.profile_street)
    private val cityField by findView<TextView>(R.id.profile_city)
    private val stateField by findView<TextView>(R.id.profile_state)
    private val natField by findView<TextView>(R.id.profile_nat)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        val extras = intent.extras
        val person = extras.getParcelable<Person>(KEY_PERSON)
        val isOwner = extras.getBoolean(KEY_OWNER)

        title = if (isOwner) "Your Profile" else person.fullName()

        with(person) {
            val imageUrl = picture.large
            val name = fullNameWithTitle()
            val email = email
            val cell = cell
            val phone = phone
            val birthday = dob.formattedWithTime()
            val registered = registered.formattedWithTime()
            val street = location.street.capitalizeAll()
            val city = location.city.capitalizeAll()
            val state = location.state.capitalizeAll()
            val nat = nat.codeToCountry()

            Picasso.with(this@ProfileActivity)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_user)
                    .transform(CropCircleTransformation())
                    .into(imageView)

            nameField.text = name
            emailField.text = email
            cellField.text = cell
            phoneField.text = phone
            birthdayField.text = birthday
            registeredField.text = registered
            streetField.text = street
            cityField.text = city
            stateField.text = state
            natField.text = nat
        }

    }
}
