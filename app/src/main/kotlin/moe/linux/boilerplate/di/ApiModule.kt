package moe.linux.boilerplate.di

import dagger.Module
import dagger.Provides
import moe.linux.boilerplate.api.GithubApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides @Singleton
    fun provideGithubApiService(builder: Retrofit.Builder): GithubApiService {
        val retrofit = builder.baseUrl("https://api.github.com").build()

        return retrofit.create(GithubApiService::class.java)
    }

    @Provides @Singleton
    fun provideRetrofitBuilder(rxJavaCallAdapterFactory: RxJavaCallAdapterFactory,
                               gsonConverterFactory: GsonConverterFactory): Retrofit.Builder =
            Retrofit.Builder()
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)

    @Provides @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides @Singleton
    fun provideRxJavaCallAdapter(): RxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()
}
