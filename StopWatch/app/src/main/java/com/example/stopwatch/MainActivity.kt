package com.example.stopwatch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0
    private var timerTask: Timer? = null

    private var isRunning = false

    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fAB.setOnClickListener{
            isRunning = !isRunning

            if(isRunning){
                start()
            }else {
                pause()
            }
        }

        LapButton.setOnClickListener {
            recordLapTime()
        }

        fAB_Reset.setOnClickListener {
            reset()
        }
    }

    private fun start(){
        fAB.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp)

        timerTask = timer(period = 10){
            time++

            val sec = time / 100
            val milli = time % 100

            runOnUiThread{
                SecTextview.text = "$sec"
                MiliTextView.text = "$milli"
            }
        }
    }

    private fun pause(){
        fAB.setImageResource((R.drawable.ic_play_circle_outline_black_24dp))

        timerTask?.cancel()//null이라면 null반환하고 ull이 아니라면 cancle을 수행한다,
    }

    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)

        textView.text = "$lap LAB : ${lapTime / 100}.${ lapTime % 100 }"

        //맨 위에 labTime
        labLayout.addView(textView, 0)
        lap++
    }

    private fun reset(){
        timerTask?.cancel()

        //모든 변수 초기화
        time = 0
        isRunning = false
        fAB.setImageResource(R.drawable.ic_play_circle_outline_black_24dp)
        SecTextview.text = "00"
        MiliTextView.text = "00"

        //모든 랩타임 제거
        labLayout.removeAllViews()
        lap = 1
    }
}