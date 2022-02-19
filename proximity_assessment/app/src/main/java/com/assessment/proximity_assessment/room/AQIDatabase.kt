package com.assessment.proximity_assessment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assessment.proximity_assessment.room.dao.AQIDao
import com.assessment.proximity_assessment.room.entity.AQI

@Database(entities = [AQI::class],version = 1,exportSchema = false)
abstract class AQIDatabase: RoomDatabase() {
    abstract fun aQiDao(): AQIDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AQIDatabase? = null

        fun getDatabase(context: Context): AQIDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AQIDatabase::class.java,
                    "word_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}