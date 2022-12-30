package com.jhon.apppokemon.features.main.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jhon.apppokemon.R
import com.jhon.data.model.bean.region.Region

class AdapterRegions(val listener: OnClickListener) : RecyclerView.Adapter<AdapterRegions.NoticiaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NoticiaViewHolder(layoutInflater.inflate(R.layout.item_region, parent, false))
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = items.size

    private var items: List<Region> = emptyList()

    //nueva data
    fun swap(items: List<Region>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(noticia: Region)
    }

    class NoticiaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val txvTitle = view.findViewById<TextView>(R.id.title)


        fun bind(beanRegions: Region, listener: OnClickListener) {

            // se configura logica de como se compartara el  view con los datos
            with(beanRegions) {
                txvTitle.text = name.toUpperCase()

                view.setOnClickListener {
                    listener.onClick(beanRegions)
                }
            }
        }

    }

}
