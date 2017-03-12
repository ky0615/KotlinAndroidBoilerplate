package moe.linux.boilerplate.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import moe.linux.boilerplate.MainApplication
import moe.linux.boilerplate.di.ActivityComponent
import moe.linux.boilerplate.di.ActivityModule
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    val activityComponent: ActivityComponent by lazy {
        (application as MainApplication).component.plus(ActivityModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
