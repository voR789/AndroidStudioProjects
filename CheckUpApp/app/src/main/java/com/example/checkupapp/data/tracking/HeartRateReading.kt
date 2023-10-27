package com.example.checkupapp.data.tracking

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeartRateReading(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "reading") val reading: Int,
    @ColumnInfo(name = "day") val day: String
)