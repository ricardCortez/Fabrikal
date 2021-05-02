package com.example.fabrikal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fabrikal.R
import com.example.fabrikal.model.ShoeHomeItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_shoe_grid_item.view.*

class ShoesHomeAdapter(var lista : ArrayList<ShoeHomeItem> ) : RecyclerView.Adapter<ShoesHomeAdapter.ViewHolder>() {
    var onItemClick: ((ShoeHomeItem) -> Unit)? = null

    fun updateList(nuevaLista : ArrayList<ShoeHomeItem>){
        lista.clear()
        lista.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(lista[absoluteAdapterPosition])
            }
        }

        fun bind(item: ShoeHomeItem){
            Picasso.get().load(item.imagen).into(itemView.photoImageView)
            itemView.marcaTextView.text = item.marca
            itemView.modeloTextView.text = item.modelo
            itemView.tipoTextView.text = item.tipo
            itemView.colorTextView.text = item.color
            itemView.precioTextView.text = item.precio.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
       val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_shoe_grid_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lista[position])
    }


}