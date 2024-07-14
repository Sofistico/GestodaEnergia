package com.example.gestodaenergia

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SessaoAdapter(private val sessoes: ArrayList<Sessao>, private val context: Context) :
    RecyclerView.Adapter<SessaoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sessao_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sessao = sessoes[position]
        holder.nameTextView.setText(sessao.name)

        holder.itemView.setOnClickListener { v: View? ->
            val intent = Intent(
                context,
                SessaoActivity::class.java
            )
            intent.putExtra("sessaoId", sessao.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return sessoes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }
}
