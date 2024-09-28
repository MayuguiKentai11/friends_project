package com.github.friendspy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.friendspy.R
import com.github.friendspy.models.Person
import com.squareup.picasso.Picasso

/*The PersonAdapter class is a custom adapter for a RecyclerView that displays a list of Person objects. Here's a breakdown of what each part of the code does:
- Class Definition
    PersonAdapter extends RecyclerView.Adapter and uses a nested PersonViewHolder class to hold the views for each item in the list.
    The adapter takes a list of Person objects and an OnItemClickListener as parameters.
- Inner Class PersonViewHolder
    PersonViewHolder is a nested class that extends RecyclerView.ViewHolder.
    It holds references to the views in each item layout (tvName, tvCell, ivPhoto, like).
    The bind method sets the data for each view and sets a click listener on the like button.
- Adapter Methods
    onCreateViewHolder: Inflates the item layout (prototype_user.xml) and creates a PersonViewHolder instance.
    getItemCount: Returns the size of the people list.
    onBindViewHolder: Binds the data to the views for the item at the given position.
- Interface OnItemClickListener
    Defines a single method onItemClick that is called when an item is clicked.*/


class PersonAdapter(private val people: List<Person>,
                    private val clickListener: OnItemClickListener) : Adapter<PersonAdapter.PersonViewHolder>() {

                        inner class PersonViewHolder(itemView : View): ViewHolder(itemView){
                            private val tvName : TextView = itemView.findViewById(R.id.tvName)
                            private val tvCell : TextView = itemView.findViewById(R.id.tvCell)
                            private val ivPhoto: ImageView = itemView.findViewById(R.id.ivPerson)
                            private val like : ImageButton = itemView.findViewById(R.id.btLike)

                            // Bind method initializes the required info for each View Object.
                            fun bind(person: Person, clickListener: OnItemClickListener) {
                                tvName.text = person.firstName + " " + person.lastName
                                tvCell.text = person.cell

                                Picasso.get().load(person.picture)
                                    .into(ivPhoto)
                                like.setOnClickListener{
                                    clickListener.onItemClick(person)
                                }
                            }
                        }

    // Inflate is parse XML to View Object. For example, Inflate Button's XML returns a Button Object.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.prototype_user, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(people[position], clickListener)
    }

    interface OnItemClickListener{
        fun onItemClick(person: Person)
    }

}