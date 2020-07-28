package ve.chinchin.app.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {

    private val prefs = "chinchin"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(prefs, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, text: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, text)

        editor.apply()
    }

    fun getValueString(KEY_NAME: String): String? {

        return sharedPref.getString(KEY_NAME, null)


    }

    fun clearSharedPreference() {

        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}