package moe.linux.boilerplate.activity

import android.os.Bundle
import moe.linux.boilerplate.R
import moe.linux.boilerplate.di.ActivityModule
import moe.linux.boilerplate.di.AppComponent

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun injectDependencies(component: AppComponent) {
        component.plus(ActivityModule(this))
                .injectTo(this)
    }
}
