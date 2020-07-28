package ve.chinchin.app.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import ve.chinchin.app.data.remote.HomeApi
import ve.chinchin.app.domain.repository.HomeRepository
import ve.chinchin.app.domain.source.HomeRemoteDataSource
import ve.chinchin.app.util.AppPreferences
import ve.chinchin.app.util.AppPreferences.URL_BASE_API
import ve.chinchin.app.viewmodel.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ve.chinchin.app.domain.usecase.HomeUseCase
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
