package moe.linux.boilerplate.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import io.reactivex.Observable
import moe.linux.boilerplate.R
import moe.linux.boilerplate.api.github.GithubApiClient
import moe.linux.boilerplate.api.qiita.QiitaApiClient
import moe.linux.boilerplate.databinding.ActivityMainBinding
import moe.linux.boilerplate.view.fragment.FrontFragment
import moe.linux.boilerplate.view.fragment.GithubListFragment
import moe.linux.boilerplate.view.fragment.QiitaListFragment
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var client: GithubApiClient

    @Inject
    lateinit var qiitaClient: QiitaApiClient

    lateinit var onStateChange: Observable<Page>

    lateinit var frontFragment: FrontFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.injectTo(this)

        initView()
        initFragment(savedInstanceState)

        onStateChange.subscribe({
            binding.toolbar.title = getString(it.title)
            when (it) {
                MainActivity.Page.FRONT -> frontFragment
                MainActivity.Page.Github -> GithubListFragment.newInstance()
                MainActivity.Page.Qiita -> QiitaListFragment.newInstance()
            }.apply {
                switchFragment(this, this.TAG)
            }
        })
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

        onStateChange = Observable.merge<Page>(listOf(
            Observable.create<Page> { subscribe ->
                binding.navView.setNavigationItemSelectedListener {
                    subscribe.onNext(Page.parseWithId(it.itemId))
                    binding.drawer.closeDrawer(GravityCompat.START)
                    true
                }
            }))
            .publish()
            .refCount()
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        frontFragment = supportFragmentManager.findFragmentByTag(FrontFragment.TAG) as FrontFragment? ?: FrontFragment.newInstance()

        if (savedInstanceState == null)
            switchFragment(frontFragment, FrontFragment.TAG)
    }

    override fun onBackPressed() {
        // TODO fragment backstackも考慮するように変更
        Timber.d("backstack count: ${supportFragmentManager.backStackEntryCount}")
        if (binding.drawer.isDrawerOpen(GravityCompat.START))
            binding.drawer.closeDrawer(GravityCompat.START)
        else if (switchFragment(frontFragment, FrontFragment.TAG))
            Timber.d("back to front page")
        else
            super.onBackPressed()
    }

    fun switchFragment(fragment: Fragment, tag: String): Boolean {
        if (fragment.isAdded) return false

        val manager = supportFragmentManager

        manager.beginTransaction().also { ft ->
            val currentFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.contentFrame)
            if (currentFragment != null)
                ft.detach(currentFragment)
            if (fragment.isDetached)
                ft.attach(fragment)
            else
                ft.add(R.id.contentFrame, fragment, tag)
        }
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

        manager.executePendingTransactions()

        return true
    }

    enum class Page(@IdRes val id: Int, @StringRes val title: Int) {
        FRONT(0, R.string.menu_top), // other page
        Github(R.id.navGithub, R.string.menu_github),
        Qiita(R.id.navQiita, R.string.menu_qiita),
        ;

        companion object {
            fun parseWithId(id: Int): Page {
                return values().find { it.id == id } ?: FRONT
            }
        }
    }
}
