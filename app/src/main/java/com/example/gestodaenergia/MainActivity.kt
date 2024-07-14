package com.example.gestodaenergia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    private var dbHelper: DBHelper? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: SessaoAdapter? = null
    private var sessaoList: ArrayList<Sessao>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.setLayoutManager(LinearLayoutManager(this))
        sessaoList = java.util.ArrayList()
        adapter = SessaoAdapter(sessaoList!!, this)
        recyclerView?.setAdapter(adapter)


        // Carregar as sessões do banco de dados
        loadSessoes()

        // Configurar o botão de adicionar sessão
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener { _: View? ->
            val intent =
                Intent(
                    this@MainActivity,
                    AddSessaoActivity::class.java
                )
            startActivity(intent)
        }

    }

    private fun loadSessoes() {
        sessaoList!!.clear()
        val db = dbHelper!!.readableDatabase
        val cursor = db.query(DBHelper.TABLE_SESSOES, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME))
                sessaoList!!.add(Sessao(id, name))
            } while (cursor.moveToNext())
        }

        cursor.close()
        adapter!!.notifyDataSetChanged()
    }
}
