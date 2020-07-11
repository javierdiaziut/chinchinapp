package gt.tribal.app

import android.app.Application
import gt.tribal.app.di.appModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


open class TribalApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        AndroidThreeTen.init(this)
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TribalApp)
            modules(
                listOf(
                    appModule
                )
            )
        }
    }
}
