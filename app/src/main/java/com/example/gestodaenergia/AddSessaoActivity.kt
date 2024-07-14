package com.example.gestodaenergia

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddSessaoActivity : AppCompatActivity() {
    private var dbHelper: DBHelper? = null
    private var nameEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sessao)

        dbHelper = DBHelper(this)
        nameEditText = findViewById(R.id.editView)

        val addButton = findViewById<Button>(R.id.button)
        addButton.setOnClickListener { v: View? -> addSessao() }
    }

    private fun addSessao() {
        val name = nameEditText!!.text.toString()
        if (!name.isEmpty()) {
            val db = dbHelper!!.writableDatabase
            val values = ContentValues()
            values.put(DBHelper.COLUMN_NAME, name)
            db.insert(DBHelper.TABLE_SESSOES, null, values)
            finish()
        }
    }
}
