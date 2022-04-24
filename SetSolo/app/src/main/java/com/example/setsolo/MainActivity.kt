package com.example.setsolo

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setsolo.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity(){

    val TAG = "MainActivity"

    private lateinit var mBinding : ActivityMainBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState!=null)
        {
            mBinding.recyclerView1.adapter = ItemAdapter(recyclerNumArray,this)
            mBinding.leftCardText.text = "남은 카드 : ${recyclerNumArrayAll.size}장"
        }
        if(recyclerNumArrayAll.size == 0){
            for(i in 1..81){
                val shape :String = if(i in 1..9||i in 28..36||i in 55..63){ "꼬불" } else if(i in 10..18||i in 37..45||i in 64..72){ "다이아" } else { "원" }

                val color : String = if(i in 1..3||i in 10..12||i in 19..21||i in 28..30||i in 37..39||i in 46..48||i in 55..57||i in 64..66||i in 73..75){ "빨강" }
                else if(i in 4..6||i in 13..15||i in 22..24||i in 31..33||i in 40..42||i in 49..51||i in 58..60||i in 67..69||i in 76..78){ "보라" }
                else{ "초록" }

                val num : Int = when {
                    i%3==1 -> { 1 }
                    i%3==2 -> { 2 }
                    else -> { 3 }
                }

                val state : String = if(i in 1..27){ "empty" }else if(i in 28..54){ "line" }else{ "fill" }

                cardArrayAll.add(CardData(color,state,num,shape,false))
            }

            for(i in 0..80){
                recyclerNumArrayAll.add(ItemData(i))
            }
        }
        else{
            mBinding.recyclerView1.adapter = ItemAdapter(recyclerNumArray,this)
            mBinding.leftCardText.text = "남은 카드 : ${recyclerNumArrayAll.size}장"
        }

        mBinding.addBtn.setOnClickListener {
            if(recyclerNumArrayAll.size!=0){
                val range = (0 until recyclerNumArrayAll.size).random()

                recyclerNumArray.add(recyclerNumArrayAll[range])
                recyclerNumArrayAll.removeAt(range)
            }

            mBinding.recyclerView1.adapter = ItemAdapter(recyclerNumArray,this)
            mBinding.leftCardText.text = "남은 카드 : ${recyclerNumArrayAll.size}장"

            chooseThreeCardArray.clear()
            threeCardPositionArray.clear()

            if(recyclerNumArrayAll.size==0){
                mBinding.addBtn.visibility = View.INVISIBLE
            }
        }

        mBinding.refreshBtn.setOnClickListener {
            mBinding.recyclerView1.adapter = ItemAdapter(recyclerNumArray,this)
        }

        mBinding.recyclerView1.layoutManager = GridLayoutManager(applicationContext,5)
        mBinding.recyclerView1.setHasFixedSize(true)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("a", 1)
    }

    fun change() {
        Log.d(TAG, "change: 변경")
        mBinding.recyclerView1.adapter = ItemAdapter(recyclerNumArray,this)
        threeCardPositionArray.clear()
        chooseThreeCardArray.clear()
    }
}

