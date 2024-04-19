package com.latihan.teamlabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    private lateinit var rvTeams: RecyclerView
    private val list = ArrayList<TeamData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        rvTeams = view.findViewById(R.id.rv_main_fragment)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvTeams.layoutManager = LinearLayoutManager(context)
        val listTeamAdapter = ListTeamAdapter(list)
        rvTeams.adapter = listTeamAdapter

        listTeamAdapter.setOnItemClickCallback(object : ListTeamAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TeamData) {
                showSelectedTeam(data)

            }
        })
        list.addAll(getListTeam())
        listTeamAdapter.notifyDataSetChanged()
    }

    private fun showSelectedTeam(team: TeamData) {
        Toast.makeText(context, "Kamu memilih ${team.name}", Toast.LENGTH_SHORT).show()
    }

    private fun getListTeam(): List<TeamData> {
        val dataName = resources.getStringArray(R.array.data_teams_name)
        val dataDescription = resources.getStringArray(R.array.data_teams_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_teams_photo)
        val listTeam = ArrayList<TeamData>()
        for (i in dataName.indices) {
            val team = TeamData(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listTeam.add(team)
        }
        dataPhoto.recycle()
        return listTeam
    }
}
