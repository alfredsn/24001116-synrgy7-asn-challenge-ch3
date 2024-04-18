package com.latihan.teamlabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListTeamAdapter(private val listTeam: ArrayList<Team>) : RecyclerView.Adapter<ListTeamAdapter
    .ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_team_row, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (
            name,
            description,
            photo
        ) = listTeam[position]

        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTeam[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Team)
    }

}