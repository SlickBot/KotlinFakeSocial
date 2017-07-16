package com.ulj.slicky.kotlinfakesocial.activity.profile

import android.app.Activity
import android.os.Bundle
import com.ulj.slicky.kotlinfakesocial.*
import com.ulj.slicky.kotlinfakesocial.activity.BackableActivity
import com.ulj.slicky.kotlinfakesocial.model.person.Person
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

import kotlinx.android.synthetic.main.profile_activity.profile_birthday as birthdayField
import kotlinx.android.synthetic.main.profile_activity.profile_cell as cellField
import kotlinx.android.synthetic.main.profile_activity.profile_city as cityField
import kotlinx.android.synthetic.main.profile_activity.profile_email as emailField
import kotlinx.android.synthetic.main.profile_activity.profile_icon as iconField
import kotlinx.android.synthetic.main.profile_activity.profile_name as nameField
import kotlinx.android.synthetic.main.profile_activity.profile_nat as natField
import kotlinx.android.synthetic.main.profile_activity.profile_phone as phoneField
import kotlinx.android.synthetic.main.profile_activity.profile_registered as registeredField
import kotlinx.android.synthetic.main.profile_activity.profile_state as stateField
import kotlinx.android.synthetic.main.profile_activity.profile_street as streetField

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
                    .into(iconField)

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
