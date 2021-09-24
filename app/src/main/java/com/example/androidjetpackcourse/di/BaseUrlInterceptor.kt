package com.example.androidjetpackcourse.di

import com.example.androidjetpackcourse.BuildConfig
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class BaseUrlInterceptor : Interceptor {
    @Volatile
    private var host: HttpUrl? = null

    fun setHost(url: String) {
        this.host = url.toHttpUrlOrNull()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = host?.let {
            val builder = chain.request().url.newBuilder()
                .scheme(it.scheme)
                .host(it.toUrl().toURI().host)
                .port(it.port)

            if(host?.toUrl()?.toURI().toString() == BuildConfig.WEATHER_API_URL)
                builder.addQueryParameter("key", BuildConfig.WEATHER_API_KEY)

            val newUrl = builder.build()

            return@let chain.request().newBuilder()
                .url(newUrl)
                .build()
        }

        return chain.proceed(newRequest!!)
    }
}

