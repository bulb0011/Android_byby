package com.android.www.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Administrator on 2017/11/24 0024.
 */

open class Text_Kotlin : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.kotlin_text)

        findViewById<TextView>(R.id.tttt).setOnClickListener(View.OnClickListener {

            val intent = Intent(this@Text_Kotlin, Kotlin_One_Text_::class.java);

            startActivity(intent);

        })

        var s="s"

        var ss="""s
            |dass你哈""".trimMargin()

        var  raw="${ss.length}"

        val s1 = "abc"
        val str = "$s1.length is ${s1.length}"

        print(s);

        print(ss);

        print(raw);

        print(str);

        Toast.makeText(this,""+raw,Toast.LENGTH_LONG).show();
        Toast.makeText(this,""+str,Toast.LENGTH_LONG).show();

        var list=listOf<String>("apple", "banana", "kiwi");

        for (li in list){

            Log.i("dengpao",li)

        }


    }



}