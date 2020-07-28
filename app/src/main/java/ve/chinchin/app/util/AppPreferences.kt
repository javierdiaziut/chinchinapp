package ve.chinchin.app.util

import android.content.SharedPreferences
import org.koin.core.KoinComponent
import org.koin.core.inject

object AppPreferences : KoinComponent {

    private val preferences: SharedPreferences by inject()

    const val SHARED_PREFERENCES_NAME = "chinchin"
    const val URL_BASE_API = "https://api.coingecko.com"
    const val DATABASE_NAME = "chinchin.sqlite"


}