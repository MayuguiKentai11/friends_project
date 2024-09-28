package com.github.friendspy.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.friendspy.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnPeople : Button

    private lateinit var btnFavorite : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        btnPeople = findViewById(R.id.btPeople)

        btnFavorite = findViewById(R.id.btFavorite)


        btnPeople.setOnClickListener{
            val intent = Intent(this, PeopleActivity::class.java)

            startActivity(intent)
        }

        btnFavorite.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)

            startActivity(intent)
        }

    }
}