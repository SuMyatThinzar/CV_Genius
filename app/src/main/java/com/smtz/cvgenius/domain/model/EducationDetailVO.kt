package com.smtz.cvgenius.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smtz.cvgenius.R

@Entity(tableName = "education")
data class EducationDetailVO (

    @ColumnInfo(name = "diplomaName")
    var diplomaName: String,

    @ColumnInfo(name = "levelOfEducation")
    var levelOfEducation: String,

    @ColumnInfo(name = "schoolName")
    var schoolName: String,

    @ColumnInfo(name = "startDate")
    var startDate: String,

    @ColumnInfo(name = "endDate")
    var endDate: String,

    @ColumnInfo(name = "credentialURL")
    var credentialURL: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    var isExpanded: Boolean = false,



    )