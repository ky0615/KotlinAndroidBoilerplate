package moe.linux.boilerplate.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import moe.linux.boilerplate.MainApplication
import moe.linux.boilerplate.di.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(MainApplication.component)
    }

    abstract fun injectDependencies(component: AppComponent)
}
