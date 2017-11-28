package com.android.www.myapplication

import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById
import java.util.*

/**
 * Created by Administrator on 2017/11/25 0025.
 */
@EActivity(R.layout.kotlin_text1)
open class Kotlin_One_Text : AppCompatActivity(){

    @ViewById(R.id.bt1)
    lateinit var bt1: TextView;

    @AfterViews
    fun init() {

        bt1.setText("AAAAAAAAAA")


        var lis =listOf<Int>(12,3,5,5,3)

        for (s in lis){

            if (s is Int){

                if (s>=5){

                    val int=s;

                    Log.i("dengpao","")
                }
            }

        }

        var string= listOf<String>()

        val list = ArrayList<String>()

        list.add("sadas")

    }



    @Click(R.id.bt1,R.id.tv2)
    open  fun click(view : View){

        when(view.id){

            R.id.bt1 ->
            {
                Toast.makeText(this@Kotlin_One_Text,"111111111",Toast.LENGTH_LONG).show();

                Log.i("dengpao","aAAAAAAAAAAAAAAAAAAAA")

            }

            R.id.tv2 -> this@Kotlin_One_Text.finish();

//            else->{}


        }


    }

}