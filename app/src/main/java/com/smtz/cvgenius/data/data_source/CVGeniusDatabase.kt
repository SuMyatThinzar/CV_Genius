package com.smtz.cvgenius.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.PersonalDetailVO
import com.smtz.cvgenius.domain.model.ProjectDetailVO

@Database(
    entities = [CvVO::class],
    version = 14,
    exportSchema = false
)

abstract class CVGeniusDatabase : RoomDatabase() {

    abstract fun cvDao(): CVDao

    companion object {
        const val DB_NAME = "CVGenius"

        var dbInstance: CVGeniusDatabase? = null

        fun getDBInstance(context: Context): CVGeniusDatabase? {
            when (dbInstance) {
                null -> dbInstance = Room.databaseBuilder(context, CVGeniusDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return dbInstance
        }
    }

}