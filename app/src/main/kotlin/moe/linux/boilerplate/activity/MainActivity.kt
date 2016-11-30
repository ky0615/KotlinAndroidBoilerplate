package moe.linux.boilerplate.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moe.linux.boilerplate.R
import moe.linux.boilerplate.api.GithubApiClient
import moe.linux.boilerplate.databinding.ActivityMainBinding
import moe.linux.boilerplate.di.ActivityModule
import moe.linux.boilerplate.di.AppComponent
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var client: GithubApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        compositeDisposable.add(
                client.githubApiService.showCommitsList("ky0615", "KotlinAndroidBoilerplate")
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
    }

    override fun injectDependencies(component: AppComponent) {
        component.plus(ActivityModule(this))
                .injectTo(this)
    }
}
