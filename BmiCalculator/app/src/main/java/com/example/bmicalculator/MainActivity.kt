@file:Suppress("DEPRECATION")

package com.example.bmicalculator

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        resultButton.setOnClickListener{

           saveData( HeighteditText.text.toString().toInt(), WeighteditText.text.toString().toInt() )
            startActivity<ResultActivity>( "weight" to WeighteditText.text.toString(), "height" to HeighteditText.text.toString())

        }

    }

    private fun saveData(height:Int, weight:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor =pref.edit()

        editor.putInt("Key_HEIGHT", height).putInt("Key_WEIGHT", weight).apply()
    }


    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("Key_HEIGTH", 0)//저장된 값이 없을 때 0을 리턴
        val weight = pref.getInt("Key_WEIGHT", 0)

        if(height != 0 && weight !=0){
            HeighteditText.setText(height.toString())
            WeighteditText.setText(height.toString())
        }
    }
}
