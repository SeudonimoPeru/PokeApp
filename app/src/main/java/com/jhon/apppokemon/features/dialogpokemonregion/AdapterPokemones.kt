package com.jhon.apppokemon.features.main.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jhon.apppokemon.R
import com.jhon.data.model.bean.PokedexResponse.PokemonEntry

class AdapterPokemones(val listener: OnClickListener) : RecyclerView.Adapter<AdapterPokemones.NoticiaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NoticiaViewHolder(layoutInflater.inflate(R.layout.item_grupo, parent, false))
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = items.size

    private var items: List<PokemonEntry> = emptyList()

    //nueva data
    fun swap(items: List<PokemonEntry>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(noticia: PokemonEntry)
    }

    class NoticiaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val txvTitle = view.findViewById<TextView>(R.id.title)
        val btnEdit = view.findViewById<View>(R.id.btnEdit)


        fun bind(pokemonEntry: PokemonEntry, listener: OnClickListener) {

            // se configura logica de como se compartara el  view con los datos
            with(pokemonEntry) {
                txvTitle.text = pokemon_species.name
            }
        }

    }

}
