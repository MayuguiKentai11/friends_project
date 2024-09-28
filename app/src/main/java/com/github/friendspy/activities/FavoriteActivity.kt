package com.github.friendspy.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.friendspy.R
import com.github.friendspy.adapters.PersonAdapter
import com.github.friendspy.db.AppDatabase
import com.github.friendspy.models.Person

class FavoriteActivity : AppCompatActivity(), PersonAdapter.OnItemClickListener {

    private lateinit var rvFavorite: RecyclerView

    @SuppressLint("MissingInflateId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorite)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rvFavorite = findViewById(R.id.rvFavorite)
    }

    override fun onResume() {
        super.onResume()

        loadPeople { people ->
            rvFavorite.adapter = PersonAdapter(people, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

    private fun loadPeople(onComplete : (List<Person>)->Unit){
        val dao = AppDatabase.getInstance(this).getDao()

        onComplete(dao.getAll())
    }

    override fun onItemClick(person: Person) {
        val dao =AppDatabase.getInstance(this).getDao()

        dao.delete(person)

        Toast.makeText(this, "Person " + person.firstName + person.lastName + " deleted from friend list!", Toast.LENGTH_SHORT).show()

        loadPeople { 
            people ->
            rvFavorite.adapter = PersonAdapter(people, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }


}