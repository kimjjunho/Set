package com.example.setsolo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cardList = ArrayList<CardData>()

        for(i in 1..81){

            val shape :String = if(i in 1..9||i in 28..36||i in 55..63){ "꼬불" } else if(i in 10..18||i in 37..45||i in 64..72){ "다이아" } else { "원" }

            val color : String = if(i in 1..3||i in 10..12||i in 19..21||i in 28..30||i in 37..39||i in 46..48||i in 55..57||i in 64..66||i in 73..75){ "빨강" }
            else if(i in 4..6||i in 13..15||i in 22..24||i in 31..33||i in 40..42||i in 49..51||i in 58..60||i in 68..70||i in 77..78){ "보라" }
            else{ "초록" }

            val num : Int = when {
                i%3==1 -> { 1 }
                i%3==2 -> { 2 }
                else -> { 3 }
            }

            val state : String = if(i in 1..27){ "empty" }else if(i in 28..54){ "line" }else{ "fill" }

            cardList.add(CardData(color,state,num,shape,true))

        }

        for(i in 0..80){
            println(cardList[i])
        }
    }
}