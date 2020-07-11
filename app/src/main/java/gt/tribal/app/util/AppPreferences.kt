package gt.tribal.app.util

import android.content.SharedPreferences
import androidx.core.content.edit
import org.koin.core.KoinComponent
import org.koin.core.inject

object AppPreferences : KoinComponent {

    private val preferences: SharedPreferences by inject()

    const val SHARED_PREFERENCES_NAME = "tribal"
    const val URL_BASE_API = "https://api.unsplash.com"
    const val DATABASE_NAME = "tribal.sqlite"


}