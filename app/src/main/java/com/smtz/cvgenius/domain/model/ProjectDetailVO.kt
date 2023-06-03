package com.smtz.cvgenius.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smtz.cvgenius.R

@Entity(tableName = "project")
data class ProjectDetailVO (

    @ColumnInfo(name = "projectTitle")
    var projectTitle: String,

    @ColumnInfo(name = "projectDescription")
    var projectDescription: String?,

    @ColumnInfo(name = "position")
    var position: String?,

    @ColumnInfo(name = "credentialURL")
    var credentialURL: String?,

    @ColumnInfo(name = "startDate")
    var startDate: String?,

    @ColumnInfo(name = "endDate")
    var endDate: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    var isExpanded: Boolean = false,
        )