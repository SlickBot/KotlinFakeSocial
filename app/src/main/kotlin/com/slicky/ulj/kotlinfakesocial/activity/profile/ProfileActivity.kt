package com.slicky.ulj.kotlinfakesocial.activity.profile

import android.app.Activity
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

        fun Activity.startOwnerProfile(owner: Person) {
            startActivity<ProfileActivity>(KEY_PERSON to owner, KEY_OWNER to true)
        }

        fun Activity.startFriendProfile(friend: Person) {
            startActivity<ProfileActivity>(KEY_PERSON to friend, KEY_OWNER to false)
        }
    }

    private val imageView       by findView<ImageView>(R.id.profile_icon)
    private val nameField       by findView<TextView>(R.id.profile_name)
    private val emailField      by findView<TextView>(R.id.profile_email)
    private val cellField       by findView<TextView>(R.id.profile_cell)
    private val phoneField      by findView<TextView>(R.id.profile_phone)
    private val birthdayField   by findView<TextView>(R.id.profile_birthday)
    private val registeredField by findView<TextView>(R.id.profile_registered)
    private val streetField     by findView<TextView>(R.id.profile_street)
    private val cityField       by findView<TextView>(R.id.profile_city)
    private val stateField      by findView<TextView>(R.id.profile_state)
    private val natField        by findView<TextView>(R.id.profile_nat)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        val extras = intent.extras
        val person = extras.getParcelable<Person>(KEY_PERSON)
        val isOwner = extras.getBoolean(KEY_OWNER)

        title = if (isOwner) "Your Profile" else person.fullName()

        with(person) {
            Picasso.with(this@ProfileActivity)
                    .load(picture.large)
                    .placeholder(R.drawable.ic_user)
                    .transform(CropCircleTransformation())
                    .into(imageView)

            nameField.text = fullNameWithTitle()
            emailField.text = email
            cellField.text = cell
            phoneField.text = phone
            birthdayField.text = dob.formattedWithTime()
            registeredField.text = registered.formattedWithTime()
            natField.text = nat.codeToCountry()

            with(location) {
                streetField.text = street.capitalizeAll()
                cityField.text = city.capitalizeAll()
                stateField.text = state.capitalizeAll()
            }
        }

    }
}
