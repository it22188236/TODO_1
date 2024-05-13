package com.example.todo_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    val save_button = findViewById<Button>(R.id.save_button)
    val create_title = findViewById<EditText>(R.id.create_title)
    val create_priority = findViewById<EditText>(R.id.create_priority)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        database = Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To-Do"
        ).build()

        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_priority.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                var title = create_title.getText().toString()
                var priority = create_priority.getText().toString()
                DataObject.setData(title, priority)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

}