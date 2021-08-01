package com.umbrella.simplepattern.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.umbrella.simplepattern.model.SomeObject

@Database(entities = [SomeObject::class], version = 1, exportSchema = false)
abstract class ObjectsDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "objects.db"
        private var database: ObjectsDatabase? = null
        private val MONITOR = Any()

        fun getInstance(context: Context): ObjectsDatabase {
            synchronized(MONITOR) {
                if (database == null) {
                    database =
                        Room.databaseBuilder(context, ObjectsDatabase::class.java, DB_NAME).build()
                }
                return database!!
            }
        }
    }

    abstract fun objectDao(): ObjectDao
}