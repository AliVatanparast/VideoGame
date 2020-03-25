package vgame.ir.view.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import android.os.Bundle

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

import com.hwangjr.rxbus.RxBus

import javax.inject.Inject

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import vgame.ir.app.AppLoader

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var dataBinding: DB

    @get:LayoutRes
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        RxBus.get().register(this)

        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.get().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        AppLoader.currentActivity = this
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentAndroidInjector
    }
}
