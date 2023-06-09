package com.github.yuang.kt.mvm.main

import android.os.Bundle
import android.view.View.GONE
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.LogUtils
import com.github.yuang.kt.android_mvvm.base.BaseActivity
import com.github.yuang.kt.android_mvvm.ext.*
import com.github.yuang.kt.mvm.databinding.ActivityMainBinding
import com.github.yuang.kt.mvm.login.LoginActivity

class MainActivity : BaseActivity() {
    private val mainViewModel by lazy { MainViewModel() }
    private lateinit var mainBinding: ActivityMainBinding

    override fun getBinding(): ViewBinding {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        return mainBinding
    }

    override fun initView(savedInstanceState: Bundle?) {
        baseToolbarBinding.toolbarTitle.text = "登录"
    }

    override fun initData() {
        mainViewModel.refreshData.vmObserverMain(this,
            onSuccess = {
                if (it.code == "200") {

                } else {
                    showToast(it.msg)
                }
            }
        )

    }

    override fun initViewModel() {
    }

    override fun setListeners() {
        super.setListeners()

        mainBinding.mBtLogin.click {
            mainViewModel.login()
        }
    }

}