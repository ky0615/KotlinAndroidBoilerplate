package moe.linux.boilerplate.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import io.reactivex.Observable
import io.reactivex.Observer
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

    lateinit var onStateChange: Observable<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

        onStateChange = Observable.create<MenuItem> { subscribe ->
            binding.navView.setNavigationItemSelectedListener {
                subscribe.onNext(it)
                binding.drawer.closeDrawer(GravityCompat.START)
                true
            }
        }.publish().refCount().apply {

        }


        onStateChange.subscribe()
        onStateChange.subscribe()


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

    fun setPage(page: Page) {

    }

    override fun injectDependencies(component: AppComponent) {
        component.plus(ActivityModule(this))
            .injectTo(this)
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START))
            binding.drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    enum class Page(@StringRes val title: Int) {
        Github(R.string.menu_github),
        Qiita(R.string.menu_qiita),
        ;
    }
}
