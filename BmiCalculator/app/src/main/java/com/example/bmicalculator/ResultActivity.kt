package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val height = intent.getStringExtra("height").toInt()
        val weight = intent.getStringExtra("weight").toInt()

        val bmi = weight / Math.pow(height/100.0, 2.0)

        //show result
        when {
            bmi >= 35 -> resultTextView.text="Altitude Obesity"
            bmi >= 30 -> resultTextView.text="Obesity"
            bmi >= 25 -> resultTextView.text="little Obesity"
            bmi>= 23 -> resultTextView.text="over Weight"
            bmi >= 18.5 -> resultTextView.text ="normal"
            else -> resultTextView.text="low Weight"
         }

        when {
            bmi >= 35 -> AdviceText.text="you devil! get away!"
            bmi >= 30 -> AdviceText.text="why are you still alive?"
            bmi >= 25 -> AdviceText.text="i recommand you suicide."
            bmi >= 23 -> AdviceText.text="go to the gym!"
            bmi >= 18.5 -> AdviceText.text="good"
        }
        when {
            bmi >= 23 -> imageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >= 18.5 ->imageView.setImageResource((R.drawable.ic_sentiment_neutral_black_24dp))
            else -> imageView.setImageResource((R.drawable.ic_sentiment_satisfied_black_24dp))
        }

        toast("bmi is $bmi")
    }
}
