package com.example.focus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //starts the timer at 25 minutes
    val startMilliSeconds = 1_500_000L
    var timer = startMilliSeconds

    //starts 5 minute timer
    val startMilliSeconds5 = 300_000L
    var timer5 = startMilliSeconds5

    //creates timer for 25 minutes
    lateinit var countDownTimer25: CountDownTimer
    //variables for 5 minute timer
    private lateinit var countDownTimer5: CountDownTimer


    lateinit var custom_title : TextView
    lateinit var pomodoroCount : TextView
    lateinit var startButton : Button
    lateinit var completedPomodoros : TextView
    lateinit var fiveMinTimer : TextView
    lateinit var reset : Button


    //variable to keep track of pomodoro cycles
    private var count: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //views
        //twentyTimerText = findViewById(R.id.twentyTimerText)
        setTimerText()

        //toolbar = findViewById(R.id.Toolbar)
        custom_title = findViewById(R.id.custom_title)
        pomodoroCount = findViewById(R.id.pomodoroCount)
        startButton = findViewById(R.id.startButton)
        completedPomodoros = findViewById(R.id.completedPomodoros)
        fiveMinTimer = findViewById(R.id.fiveMinTimer)
        reset = findViewById(R.id.reset)

    }

    fun on(view: View){
        when(view.id){
            R.id.startButton -> startTimer25()
            R.id.reset -> resetTimer()
        }
    }

    private fun startTimer25(){
        countDownTimer25 = object : CountDownTimer(timer, 1000){
            override fun onFinish(){
                Toast.makeText(this@MainActivity,"end timer",Toast.LENGTH_SHORT).show()
                startTimer5()
            }
            override fun onTick(millisUntilFinished: Long){
                timer = millisUntilFinished
                setTimerText()
            }
        }.start()
    }


    private fun resetTimer(){
        countDownTimer25.cancel()
        timer = startMilliSeconds
        setTimerText()
    }

    fun setTimerText(){
        var minutes = (timer / 1000) / 60
        var seconds = (timer / 1000) % 60

        var format = String.format("%02d:%02d", minutes, seconds)

        twentyTimerText.text = format
    }

    private fun startTimer5(){
        countDownTimer5 = object : CountDownTimer(timer5, 1000){
            override fun onFinish(){
                Toast.makeText(this@MainActivity,"end timer",Toast.LENGTH_SHORT).show()
                count+= 1
                pomodoroCount.text = "Pomodoro Count:$count"
                if (count > 1){
                    completedPomodoros.text = "You have completed $count Pomodoros! Time for a break."
                }
                else{
                    completedPomodoros.text = "You have completed $count Pomodoro! Time for a break."
                }
                reset5Timer()
                resetTimer()
                startTimer25()
            }
            override fun onTick(millisUntilFinished: Long){
                timer5 = millisUntilFinished
                set5TimerText()
            }
        }.start()
    }


    private fun reset5Timer(){
        countDownTimer5.cancel()
        timer5 = startMilliSeconds5
        setTimerText()
    }

    fun set5TimerText(){
        var minutes = (timer5 / 1000) / 60
        var seconds = (timer5 / 1000) % 60

        var format = String.format("%02d:%02d", minutes, seconds)

        fiveMinTimer.text = format
    }

}





