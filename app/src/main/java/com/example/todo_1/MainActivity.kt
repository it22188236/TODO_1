package com.example.todo_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
//    val add = findViewById<Button>(R.id.add)
      lateinit var add:Button
    val deleteAll = findViewById<Button>(R.id.deleteAll)
    val recycler_view = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java,"ToDo"
        ).build()


        add = findViewById(R.id.add)
//        add.setOnClickListener()

//        fun Submit(v: View){
//            val intent = Intent(this, CreateCard::class.java)
//            startActivity(intent)
//            finish()
//        }

//        add.setOnClickListener {
//            val intent = Intent(this, CreateCard::class.java)
//            startActivity(intent)
//        }

//        deleteAll.setOnClickListener {
//            DataObject.deleteAll()
//            GlobalScope.launch {
//                database.dao().deleteAll()
//            }
//            setRecycler()
//        }

        //setRecycler()


    }

    fun Submit(v: View){
        val intent = Intent(this, CreateCard::class.java)
        startActivity(intent)
        finish()
    }

//    fun setRecycler() {
//        recycler_view.adapter = Adapter(DataObject.getAllData())
//        recycler_view.layoutManager = LinearLayoutManager(this)
//    }
}