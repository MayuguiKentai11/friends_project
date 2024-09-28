package com.github.friendspy.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person (
    @PrimaryKey
    val id : Int? = null,
    @ColumnInfo(name = "first_name")
    val firstName : String?,
    @ColumnInfo("last_name")
    val lastName: String?,
    @ColumnInfo("email")
    val email: String?,
    @ColumnInfo("cell")
    val cell : String?,
    @ColumnInfo("picture")
    val picture : String?
)
