package com.example.checkupapp.data.userInfo
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "height") val height: Int, // In centimeters
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "gender") val gender: Boolean // True for males, false for females
)
