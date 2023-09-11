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

    @ColumnInfo(name = "referenceName")
    var referenceName: String,

    @ColumnInfo(name = "position")
    var position: String?,

    @ColumnInfo(name = "companyName")
    var companyName: String?,

    @ColumnInfo(name = "emailAddress")
    var emailAddress: String?,

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String?,

    @ColumnInfo(name = "others")
    var others: String?,

    )