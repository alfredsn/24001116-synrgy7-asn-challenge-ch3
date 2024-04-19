package com.latihan.teamlabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PlayerFragment : Fragment() {

    private lateinit var rvPlayers: RecyclerView
    private val list = ArrayList<PlayerData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvPlayers.layoutManager = LinearLayoutManager(context)
        val listPlayerAdapter = ListPlayerAdapter(list)
        rvPlayers.adapter = listPlayerAdapter

        listPlayerAdapter.setOnItemClickCallback(object : ListPlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PlayerData) {
                showSelectedPlayer(data)
            }
        })
        list.addAll(getListPlayer())
        listPlayerAdapter.notifyDataSetChanged()
    }

    private fun showSelectedPlayer(player: PlayerData) {
        Toast.makeText(context, "Kamu memilih ${player.name}", Toast.LENGTH_SHORT).show()
    }

    private fun getListPlayer(): List<PlayerData> {
        val dataName = resources.getStringArray(R.array.data_players_name)
        val dataDescription = resources.getStringArray(R.array.data_players_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_players_photo)
        val listPlayer = ArrayList<PlayerData>()
        for (i in dataName.indices) {
            val player = PlayerData(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listPlayer.add(player)
        }
        dataPhoto.recycle()
        return listPlayer
    }
}