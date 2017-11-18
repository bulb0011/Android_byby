package com.android.www.myapplication.utils

import com.android.www.myapplication.ben.ResultData
import ikidou.reflect.TypeBuilder
import java.lang.reflect.Type

/**
 * Created by Administrator on 2017/11/15 0015.
 */
object TypeUtils {
    fun <T> getType(cls: Class<T>): Type = TypeBuilder.newInstance(ResultData::class.java).addTypeParam(cls).build()

    fun <T> getListType(cls: Class<T>): Type = TypeBuilder.newInstance(ResultData::class.java)
            .beginSubType(List::class.java)
            .addTypeParam(cls)
            .endSubType()
            .build()
}