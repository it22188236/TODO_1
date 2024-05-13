package com.example.todo_1

//import kotlinx.android.synthetic.main.activity_update_card.*
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    val create_title = findViewById<EditText>(R.id.create_title)
    val create_priority = findViewById<EditText>(R.id.create_priority)
    val delete_button = findViewById<Button>(R.id.delete_button)
    val update_button = findViewById<Button>(R.id.update_button)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        database = Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"TODO"
        ).build()

        val pos = intent.getIntExtra("id",-1)
        if(pos != -1){
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            create_title.setText(title)
            create_priority.setText(priority)

            delete_button.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }
                myIntent()
            }

            update_button.setOnClickListener {
                DataObject.updateData(
                    pos,
                    create_title.text.toString(),
                    create_priority.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1, create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }
                myIntent()
            }
        }

    }
    fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}