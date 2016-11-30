package moe.linux.boilerplate.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import moe.linux.boilerplate.api.github.GithubApiService
import moe.linux.boilerplate.api.qiita.QiitaApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides @Singleton
    fun provideGithubApiService(rxJavaCallAdapterFactory: RxJava2CallAdapterFactory,
                                gsonConverterFactory: GsonConverterFactory): GithubApiService =
            Retrofit.Builder()
                    .client(OkHttpClient.Builder()
                            .addInterceptor {
                                val original = it.request()
                                Timber.d("github intercept: url: ${original.url()}")

                                it.proceed(original)
                            }.build())
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .baseUrl("https://api.github.com/")
                    .build()
                    .create(GithubApiService::class.java)

    @Provides @Singleton
    fun provideQiitaApiService(rxJavaCallAdapterFactory: RxJava2CallAdapterFactory,
                               gsonConverterFactory: GsonConverterFactory): QiitaApiService =
            Retrofit.Builder()
                    .client(OkHttpClient.Builder()
                            .addInterceptor {
                                val original = it.request()
                                Timber.d("qiita intercept: url: ${original.url()}")

                                it.proceed(original)
                            }.build())
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .baseUrl("https://qiita.com/api/v2/")
                    .build()
                    .create(QiitaApiService::class.java)

    @Provides @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides @Singleton
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
}
