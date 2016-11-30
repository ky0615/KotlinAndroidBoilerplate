package moe.linux.boilerplate.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import moe.linux.boilerplate.api.GithubApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides @Singleton
    fun provideGithubApiService(builder: Retrofit.Builder): GithubApiService {
        val retrofit = builder.baseUrl("https://api.github.com").build()

        return retrofit.create(GithubApiService::class.java)
    }

    @Provides @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient,
                               rxJavaCallAdapterFactory: RxJava2CallAdapterFactory,
                               gsonConverterFactory: GsonConverterFactory): Retrofit.Builder =
            Retrofit.Builder()
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)

    @Provides @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor {
            val original = it.request()
            Timber.d("intercept: url: ${original.url()}")

            it.proceed(original)
        }

        return builder.build()
    }

    @Provides @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides @Singleton
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
}
