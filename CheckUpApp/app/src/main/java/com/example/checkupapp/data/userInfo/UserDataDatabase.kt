package com.example.checkupapp.data.userInfo


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class UserDataDatabase : RoomDatabase() {

    abstract fun userDataDAO(): UserDAO

    companion object {
        @Volatile
        private var Instance: UserDataDatabase? = null

        fun getDatabase(context: Context): UserDataDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UserDataDatabase::class.java, "User Data Database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}