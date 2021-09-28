package com.example.androidjetpackcourse.di

import android.app.Application
import androidx.room.Room
import com.example.androidjetpackcourse.BuildConfig
import com.example.androidjetpackcourse.data.database.NoteDao
import com.example.androidjetpackcourse.data.database.NoteRoomDatabase
import com.example.androidjetpackcourse.data.network.GitRepoApi
import com.example.androidjetpackcourse.data.network.GitRepoRepository
import com.example.androidjetpackcourse.data.network.WeatherApi
import com.example.androidjetpackcourse.data.network.WeatherRepository
import com.example.androidjetpackcourse.handlers.ResponseHandler
import com.example.androidjetpackcourse.viewmodel.GitRepoViewModel
import com.example.androidjetpackcourse.viewmodel.NoteViewModel
import com.example.androidjetpackcourse.viewmodel.WeatherViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {

    // the UserViewModel class need a instance of UserRepository as parameter
    // so we use the get() to tell koin to retrieve it for us
    viewModel {
        GitRepoViewModel(get())
    }

    viewModel {
        NoteViewModel(get())
    }

    viewModel {
        WeatherViewModel(get())
    }
}


val repositoryModule = module {
    single {
        GitRepoRepository(get(), get())
    }

    single {
        WeatherRepository(get(), get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): GitRepoApi {
        return retrofit.create(GitRepoApi::class.java)
    }

    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    single { provideUseApi(get()) }
    single { provideWeatherApi(get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application): NoteRoomDatabase {
        return Room.databaseBuilder(application, NoteRoomDatabase::class.java, "notes")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: NoteRoomDatabase): NoteDao {
        return  database.noteDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }


}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(interceptor: BaseUrlInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_API_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    // single tell to Koin to create a singleton
    // the instance will be created only once during the execution of the application
    single { BaseUrlInterceptor() }
    single { provideGson() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
    factory { ResponseHandler() }
}
