package com.example.gestodaenergia

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class AddRegistroActivity : AppCompatActivity() {
    private var dbHelper: DBHelper? = null
    private var quantidadeEditText: EditText? = null
    private var sessaoId = 0
    private var tipo = "gerada" // Tipo padrão é "gerada"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_registro)

        sessaoId = intent.getIntExtra("sessaoId", -1)
        dbHelper = DBHelper(this)
        quantidadeEditText = findViewById(R.id.quantidadeEditText)

        // Configurar os botões de rádio para selecionar o tipo de energia
        val geradaRadioButton = findViewById<RadioButton>(R.id.geradaRadioButton)
        val consumidaRadioButton = findViewById<RadioButton>(R.id.consumidaRadioButton)

        geradaRadioButton.setOnClickListener { v: View? ->
            tipo = "gerada"
        }
        consumidaRadioButton.setOnClickListener { v: View? ->
            tipo = "consumida"
        }

        // Configurar o botão de adicionar registro
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener { v: View? -> addRegistro() }
    }

    private fun addRegistro() {
        val quantidadeStr = quantidadeEditText!!.text.toString()
        if (!quantidadeStr.isEmpty()) {
            val quantidade = quantidadeStr.toDouble()
            val db = dbHelper!!.writableDatabase
            val values = ContentValues()
            values.put(DBHelper.COLUMN_SESSAO_ID, sessaoId)
            values.put(DBHelper.COLUMN_TIPO, tipo)
            values.put(DBHelper.COLUMN_QUANTIDADE, quantidade)
            db.insert(DBHelper.TABLE_REGISTROS, null, values)
            finish()
        }
    }
}