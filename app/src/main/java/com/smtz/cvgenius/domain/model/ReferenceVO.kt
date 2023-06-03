package com.smtz.cvgenius.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smtz.cvgenius.R

@Entity(tableName = "reference")
data class ReferenceVO (

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    )