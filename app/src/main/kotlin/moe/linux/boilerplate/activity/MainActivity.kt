package moe.linux.boilerplate.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import moe.linux.boilerplate.R
import moe.linux.boilerplate.api.github.GithubApiClient
import moe.linux.boilerplate.api.qiita.QiitaApiClient
import moe.linux.boilerplate.databinding.ActivityMainBinding
import moe.linux.boilerplate.di.ActivityModule
import moe.linux.boilerplate.di.AppComponent
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var client: GithubApiClient

    @Inject
    lateinit var qiitaClient: QiitaApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        compositeDisposable.add(
                client.showCommitsList("ky0615", "KotlinAndroidBoilerplate")
                        .subscribe { list, throwable ->
                            if (throwable != null) {
                                Timber.e(throwable)
                                return@subscribe
                            }
                            list.forEach {
                                Timber.d("commit message: ${it.commit.message}")
                            }
                        }
        )

        compositeDisposable.add(
                qiitaClient.stockList("dll7")
                        .subscribe { list, throwable ->
                            if (throwable != null) {
                                Timber.e(throwable)
                                return@subscribe
                            }

                            list.forEach {
                                Timber.d("Stock title: ${it.title}")
                            }
                        }
        )
    }

    override fun injectDependencies(component: AppComponent) {
        component.plus(ActivityModule(this))
                .injectTo(this)
    }
}
