package com.latihan.teamlabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvTeams: RecyclerView
    private val list = ArrayList<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTeams = findViewById(R.id.rv_teams)
        rvTeams.setHasFixedSize(true)

        list.addAll(getListTeam())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvTeams.layoutManager = LinearLayoutManager(this)
        val listTeamAdapter = ListTeamAdapter(list)
        rvTeams.adapter = listTeamAdapter

        listTeamAdapter.setOnItemClickCallback(object: ListTeamAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Team) {
                showSelectedTeam(data)
            }
        })
    }

    private fun getListTeam(): Collection<Team> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listTeam = ArrayList<Team>()
        for (i in dataName.indices) {
            val team = Team(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listTeam.add(team)
        }
        return listTeam
    }

    private fun showSelectedTeam(team: Team) {
        Toast.makeText(this, "Kamu memilih " + team.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvTeams.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvTeams.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}