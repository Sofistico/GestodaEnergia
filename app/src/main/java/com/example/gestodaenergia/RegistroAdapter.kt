package com.example.gestodaenergia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.Int

class RegistroAdapter(private val registros: ArrayList<Registro>) :
    RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.registro_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val registro = registros[position]
        holder.tipoTextView.setText(registro.tipo)
        holder.quantidadeTextView.setText(registro.quantidade.toString())
    }

    override fun getItemCount(): Int {
        return registros.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tipoTextView: TextView = itemView.findViewById(R.id.tipoTextView)
        var quantidadeTextView: TextView = itemView.findViewById(R.id.quantidadeTextView)
    }
}
