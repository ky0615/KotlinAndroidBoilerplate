package moe.linux.boilerplate.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import moe.linux.boilerplate.R
import moe.linux.boilerplate.databinding.ActivityMainBinding
import moe.linux.boilerplate.di.ActivityModule
import moe.linux.boilerplate.di.AppComponent

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun injectDependencies(component: AppComponent) {
        component.plus(ActivityModule(this))
                .injectTo(this)
    }
}
