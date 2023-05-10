package com.github.yuang.kt.android_mvvm.entity

/**
 * @author AnglePenCoding
 * Created by on 2023/2/17 0017
 * @website https://github.com/AnglePengCoding
 */
open class BaseData<T>(
    private val msg: String,
    val result: T,
    val ext: String,
    val code: String
) {

    /**
     * 数据是否正确，默认实现
     */
    open fun dataRight(): Boolean {
        return code == "200"
    }

    /**
     * 获取错误信息，默认实现
     */
    open fun getMsg(): String {
        return msg
    }
}