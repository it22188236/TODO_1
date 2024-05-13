package com.example.todo_1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private lateinit var database: myDatabase
//    private var mDelayHandler :Handler?=null
//    private val splash_delay:Long=3000
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        GlobalScope.launch {
            DataObject.listdata = database.dao().getTask() as MutableList<CardInfo>
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
        }, 2000)

//        mDelayHandler = Handler()
//        mDelayHandler?.postDelayed(mRunnable,splash_delay)
    }

//    private val mRunnable:Runnable = Runnable{
//        val intent = Intent(this,MainActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
}