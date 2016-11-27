package moe.linux.boilerplate.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import moe.linux.boilerplate.di.scope.AppQualifier
import okhttp3.OkHttpClient
import rx.subscriptions.CompositeSubscription
import timber.log.Timber
import javax.inject.Singleton

@Module
class AppModule(private val context: Application) {

    @Provides @Singleton
    fun provideApplication(): Application = context

    @Provides @Singleton @AppQualifier
    fun provideContext(): Context = context.baseContext

    @Provides @Singleton
    fun provideResources(): Resources = context.resources

    @Provides @Singleton
    fun provideLayoutInflater(@AppQualifier context: Context): LayoutInflater
            = LayoutInflater.from(context)

    @Provides
    fun provideDebugTree(): Timber.DebugTree = Timber.DebugTree()

    @Provides
    fun provideOkHttpCLient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor {
            val original = it.request()
            Timber.d("intercept: url: ${original.url()}")

            it.proceed(original)
        }

        return builder.build()
    }

    @Provides
    fun provideCompositeSubscription(): CompositeSubscription = CompositeSubscription()


    @Provides @Singleton
    fun providePicasso(context: Context): Picasso {
        val builder = Picasso.Builder(context)
        val picasso = builder.build()
        Picasso.setSingletonInstance(picasso)
        return picasso
    }

}