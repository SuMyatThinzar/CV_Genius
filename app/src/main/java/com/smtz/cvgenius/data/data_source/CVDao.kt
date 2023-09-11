package com.smtz.cvgenius.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smtz.cvgenius.domain.model.CvVO

@Dao
interface CVDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewCV(cvVO: CvVO?)

    @Query("SELECT * FROM Cv WHERE cvId = :cvId")
    fun getSingleCV(cvId: Long): LiveData<CvVO?>

    @Query("SELECT * FROM Cv")
    fun getAllCV(): LiveData<List<CvVO>>?

    @Query("DELETE FROM Cv WHERE cvId = :cvId")
    fun deleteCV(cvId: Long)

}