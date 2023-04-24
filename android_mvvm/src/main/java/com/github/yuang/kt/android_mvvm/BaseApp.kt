package com.github.yuang.kt.android_mvvm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import me.jessyan.autosize.AutoSize
import me.jessyan.autosize.AutoSizeConfig
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins.init

/**
 * @author AnglePenCoding
 * Created by on 2023/2/17 0017
 * @website https://github.com/AnglePengCoding
 */
abstract class BaseApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: BaseApp

        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, layout ->
            layout.apply {
                setEnableLoadMore(false)
                autoRefresh()
                finishRefresh(2000)
                setPrimaryColorsId(R.color.font_blue, android.R.color.white)
            }
            ClassicsHeader(mContext)
        }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        mContext = this
        initAutoSize()
        initRxHttp()
    }

    private fun initRxHttp() {
        val builder = OkHttpClient.Builder()
        val chuckInterceptor = ChuckInterceptor(mContext)
        chuckInterceptor.showNotification(false)
        builder.addInterceptor(chuckInterceptor)

        init(builder.build()).setDebug(true)
    }

    private fun initAutoSize() {
        AutoSize.initCompatMultiProcess(mContext)
        AutoSizeConfig.getInstance().apply {
            isBaseOnWidth = true
            isCustomFragment = true
        }
    }


}