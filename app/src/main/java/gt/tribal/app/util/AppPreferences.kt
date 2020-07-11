package cl.blackmind.app.util

import android.content.SharedPreferences
import androidx.core.content.edit
import org.koin.core.KoinComponent
import org.koin.core.inject

object AppPreferences : KoinComponent {

    private val preferences: SharedPreferences by inject()

    const val AUTH_TOKEN_DEFAULT = "authTokenDefault"
    private const val AUTH_TOKEN = "authToken"
    private const val FIREBASE_TOKEN = "firebaseToken"
    const val SHARED_PREFERENCES_NAME = "notes"

    var authToken: String?
        get() = preferences.getString(AUTH_TOKEN, AUTH_TOKEN_DEFAULT)
        set(value) = preferences.edit {
            putString(AUTH_TOKEN, "Bearer $value").apply()
        }

    var firebaseToken: String?
        get() = preferences.getString(FIREBASE_TOKEN, "")
        set(value) = preferences.edit {
            putString(FIREBASE_TOKEN, value).apply()
        }

    fun cleanSession(): Boolean {
        preferences.edit().remove(AUTH_TOKEN).apply()
        return authToken == null
    }

}