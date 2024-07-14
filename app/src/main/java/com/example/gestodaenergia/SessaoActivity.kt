package com.example.gestodaenergia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SessaoActivity : AppCompatActivity() {
    private var dbHelper: DBHelper? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: RegistroAdapter? = null
    private var registroList: ArrayList<Registro>? = null
    private var sessaoId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sessao)

        sessaoId = intent.getIntExtra("sessaoId", -1)
        dbHelper = DBHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.setLayoutManager(LinearLayoutManager(this))
        registroList = ArrayList()
        adapter = RegistroAdapter(registroList!!)
        recyclerView?.setAdapter(adapter)

        // Carregar os registros do banco de dados
        loadRegistros()

        // Configurar o botão de adicionar registro
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener { v: View? ->
            val intent =
                Intent(
                    this@SessaoActivity,
                    AddRegistroActivity::class.java
                )
            intent.putExtra("sessaoId", sessaoId)
            startActivity(intent)
        }
    }

    private fun loadRegistros() {
        registroList!!.clear()
        val db = dbHelper!!.readableDatabase
        val selection = DBHelper.COLUMN_SESSAO_ID + " = ?"
        val selectionArgs = arrayOf(sessaoId.toString())
        val cursor =
            db.query(DBHelper.TABLE_REGISTROS, null, selection, selectionArgs, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_TIPO))
                val quantidade =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_QUANTIDADE))
                registroList!!.add(Registro(id, sessaoId, tipo, quantidade))
            } while (cursor.moveToNext())
        }

        cursor.close()
        adapter!!.notifyDataSetChanged()
    }
}
