package com.example.checkupapp.data.tracking

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HeartRateReading::class], version = 1, exportSchema = false)
abstract class HeartRateDatabase : RoomDatabase() {

    abstract fun heartRateDAO(): HeartRateDAO

    companion object {
        @Volatile
        private var Instance: HeartRateDatabase? = null

        fun getDatabase(context: Context): HeartRateDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HeartRateDatabase::class.java, "Heart Rate Database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
