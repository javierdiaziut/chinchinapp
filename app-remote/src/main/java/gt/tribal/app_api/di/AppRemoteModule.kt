package cl.blackmind.app_api.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.koin.dsl.module

val appRemoteModule = module {

    val loggin = HttpLoggingInterceptor()
    loggin.level = HttpLoggingInterceptor.Level.BODY

    /* Retrofit */
    single {
        OkHttpClient.Builder()
            //.callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
//            .addInterceptor(ChuckInterceptor(androidContext()))
            //.addInterceptor(AuthInterceptor(AppPreferences))
            //.addInterceptor(RefreshAuthTokenInterceptor(AppPreferences))
            .addInterceptor(loggin)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}