package com.s.hkaccount.ui

import android.app.Application
import android.content.pm.PackageManager
import android.util.Log

/**
 * Create by chenjunsheng on 2020/1/5
 */
class HkAccountApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val appInfo = packageManager
            .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        val key = appInfo.metaData.getString("juhe_key")
        Log.d("cjslog", "appkey:${key}")
    }
}