package cl.blackmind.app.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import cl.blackmind.app.URL_BASE_EXPERIENCE_API
import cl.blackmind.app.util.AppPreferences
import cl.blackmind.app.data.interceptor.AuthInterceptor
import cl.blackmind.app.data.interceptor.RefreshAuthTokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    /* Android Services */
    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            AppPreferences.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }

    //single { get<Retrofit>().create(LoginRemoteApi::class.java) as LoginRemoteApi }


    /* Database */
//    single {
//        Room.databaseBuilder(
//            androidApplication(),
//            AppDatabase::class.java,
//            DATABASE_NAME
//        ).fallbackToDestructiveMigration().build()
//    }

    /* Dao Interfaces */
    //factory { get<AppDatabase>().userDao() }


    /* DataSource */
    //factory { UserLocalDataSource(get()) }


    /* Repositories */
    //factory { UserRepository(get(), get()) }

    /* View models */
    //viewModel { LoginViewModel(get(), get(), get()) }

    /* UseCases */
    //factory { LoginUseCase(get()) }


    /* Picasso */
//    single {
//        Picasso.get()
//    }

}
