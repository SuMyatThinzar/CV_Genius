package com.smtz.cvgenius.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workExperience")
class WorkExperienceVO(

    @ColumnInfo(name = "position")
    val position: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "startDate")
    var startDate: String?,

    @ColumnInfo(name = "endDate")
    var endDate: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    )