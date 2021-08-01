package com.umbrella.simplepattern.model.database

import androidx.room.*
import com.umbrella.simplepattern.model.SomeObject

@Dao
interface ObjectDao {
    @Query("SELECT * FROM someObject")
    fun getAllObjects(): List<SomeObject>

    @Query("DELETE FROM someObject")
    fun deleteAllObjects()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertObject(someObject: SomeObject)

    @Delete
    fun deleteObject(someObject: SomeObject)
}