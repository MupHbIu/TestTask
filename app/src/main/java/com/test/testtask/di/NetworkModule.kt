package com.test.testtask.di

import com.test.testtask.data.repository.AuthorizationRepositoryImp
import com.test.testtask.data.repository.NetworkImagesRepository
import com.test.testtask.data.source.RetrofitService
import com.test.testtask.data.source.RetrofitServiceAuthorization
import com.test.testtask.domain.repository.AuthorizationRepository
import com.test.testtask.domain.repository.ImagesRepository
import com.test.testtask.util.Constants.BASE_URL_IMAGES
import com.test.testtask.util.ConstantsPrivate.APPID
import com.test.testtask.util.ConstantsPrivate.BASE_URL_AUTHORIZATION
import dagger.Module
import dagger.Provides
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
            okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_IMAGES)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("AuthorizationRetrofit")
    fun provideAuthRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
            @Named("AuthorizationOkHttpClient") okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_AUTHORIZATION)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Named("AuthorizationOkHttpClient")
    @Singleton
    fun provideAuthOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(interceptor)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        // OLD
        /*
        val original = chain.request()
        val originalUrl = original.url()
        var urlString = "${BASE_URL_AUTHORIZATION}${originalUrl.encodedPath()}"
        var str = originalUrl.encodedPath()
        str += "&appid=$APPID"


        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .addHeader("appid", APPID) // <-- this is the important line

        val request = requestBuilder.build()
        chain.proceed(request)
        */

        // NEW
        val request = chain.request()
        val url = request.url()
        val newRequest = request.newBuilder()
                .url(url.newBuilder()
                        .addQueryParameter("appid", APPID)
                        .build()
                )
                .build()
        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService =
        retrofit.create(RetrofitService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitServiceAuthorization(@Named("AuthorizationRetrofit") retrofit: Retrofit): RetrofitServiceAuthorization =
            retrofit.create(RetrofitServiceAuthorization::class.java)

    @Provides
    @Singleton
    fun provideImagesRepository(retrofitService: RetrofitService): ImagesRepository =
        NetworkImagesRepository(retrofitService)

    @Provides
    @Singleton
    fun provideAuthorizationRepository(retrofitServiceAuthorization: RetrofitServiceAuthorization): AuthorizationRepository =
            AuthorizationRepositoryImp(retrofitServiceAuthorization)
}
