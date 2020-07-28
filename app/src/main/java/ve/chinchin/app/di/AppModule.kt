package gt.tribal.app.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.room.Room
import gt.tribal.app.data.remote.HomeApi
import gt.tribal.app.domain.repository.HomeRepository
import gt.tribal.app.domain.source.HomeRemoteDataSource
import gt.tribal.app.domain.usecase.*
import gt.tribal.app.util.AppPreferences
import gt.tribal.app.util.AppPreferences.DATABASE_NAME
import gt.tribal.app.util.AppPreferences.URL_BASE_API
import gt.tribal.app.viewmodel.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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

    val loggin = HttpLoggingInterceptor()
    loggin.level = HttpLoggingInterceptor.Level.BODY

    /* Retrofit */
    single {
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(loggin)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(URL_BASE_API)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(HomeApi::class.java) as HomeApi }


      /* DataSource */
    factory { HomeRemoteDataSource(get()) }


    /* Repositories */
    factory { HomeRepository(get()) }


    /* View models */
    viewModel { HomeViewModel(get()) }

    /* UseCases */
    factory { HomeUseCase(get()) }



}
