package moe.linux.boilerplate.activity

import android.support.v7.app.AppCompatActivity
import moe.linux.boilerplate.di.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    abstract fun injectDependencies(component: AppComponent)
}
