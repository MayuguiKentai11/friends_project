package com.github.friendspy.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.friendspy.R
import com.github.friendspy.adapters.PersonAdapter
import com.github.friendspy.communication.ApiResponse
import com.github.friendspy.db.AppDatabase
import com.github.friendspy.models.Person
import com.github.friendspy.network.RandomUserApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeopleActivity : AppCompatActivity(), PersonAdapter.OnItemClickListener {

    // Defining the variables for the View Components (Button, RecyclerView and EditText)
    private lateinit var btnLoad: Button

    private lateinit var etResults: EditText

    private lateinit var rvPeople: RecyclerView

    // Initialize the variables
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_people)

        setSupportActionBar(findViewById(R.id.toolbar2))

        btnLoad = findViewById(R.id.btLoadPeople)

        etResults = findViewById(R.id.etPeopleCount)

        rvPeople = findViewById(R.id.rvPeople)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


    //
    override fun onResume() {
        super.onResume()


        // In case of clicking the Load button, its desencadenate an event.
        btnLoad.setOnClickListener {
            val results = etResults.text.toString().toInt()
            loadPeople(results){
                // ALL THIS ACTIONS IS A CALLBACK, AFTER OF THE EXEC OF loadPeople function.
                people ->
                rvPeople.adapter = PersonAdapter(people, this) // First parameter, the adapter (card for the person) and the context where is calling
                // IMPORTANT
                rvPeople.layoutManager = LinearLayoutManager(this@PeopleActivity) // Setting the layout manager into a LinearLayoutManager, arranges the items in a vertical list.
            }
        }

    }


    private fun loadPeople(results : Int, onComplete : (List<Person>) -> Unit){
        var count = results // Initialize the count of people to show

        // Retrofit is the HttpClient for Android and Java
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/") // Sets the base url to request
            .addConverterFactory(GsonConverterFactory.create()) // JsonConverter to Kotlin or Java classes
            .build()

        // Sets the config to randomUserApiService
        val randomUserService: RandomUserApiService = retrofit.create(RandomUserApiService::class.java)

        // Make the request and receive the values
        val request = randomUserService.getUsers(count)

        request.enqueue(object : Callback<ApiResponse>{ // Enqueue the answer to successfully be executed asynchronously
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful){ // Check if is successfully
                    val userApiUsers: ApiResponse = response.body()!!

                    val personList = mutableListOf<Person>()

                    userApiUsers.results?.forEach{
                        personList.add(it.toPerson())
                    }

                    onComplete(personList)

                }
            }


            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })

    }


    override fun onItemClick(person: Person) {
        val dao= AppDatabase.getInstance(this).getDao()

        dao.insertOne(person)

        Toast.makeText(this, "Person " + person.firstName + "added to the friend list", Toast.LENGTH_SHORT).show()
    }
}