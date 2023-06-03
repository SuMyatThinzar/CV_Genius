package com.smtz.cvgenius.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal")
data class PersonalDetailVO(

    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String?,

    @ColumnInfo(name = "contact")
    var contact: String,

    @ColumnInfo(name = "dateOfBirth")
    var dateOfBirth: String?,

    @ColumnInfo(name = "nationality")
    var nationality: String?,

    @ColumnInfo(name = "gender")
    var gender: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "address")
    var address: String?,

    @ColumnInfo(name = "professionalTitle")
    var professionalTitle: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    var isExpanded: Boolean = false,
        )