package com.smtz.cvgenius.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.smtz.cvgenius.data.typeConverters.*

@Entity(tableName = "Cv")

@TypeConverters(
    PersonalDetailTypeConverter::class,
    EducationDetailTypeConverter::class,
    WorkExperienceTypeConverter::class,
    SkillTypeConverter::class,
    AchievementTypeConverter::class,
    ProjectDetailTypeConverter::class,
    ReferenceTypeConverter::class,
    ProfileImageTypeConverter::class,
)

data class CvVO (

    @ColumnInfo(name = "templateId")
    var templateId: Int,

    @ColumnInfo(name = "profileImage")
    var profileImage: ByteArray?,

    @ColumnInfo(name = "personalDetails")
    var personalDetails: PersonalDetailVO?,

    @ColumnInfo(name = "educationDetails")
    var educationDetails: MutableList<EducationDetailVO>,

    @ColumnInfo(name = "workExperiences")
    var workExperiences: MutableList<WorkExperienceVO>,

    @ColumnInfo(name = "skills")
    var skills: MutableList<SkillsVO>,

    @ColumnInfo(name = "achievements")
    var achievements: MutableList<AchievementVO>,

    @ColumnInfo(name = "objective")
    var objective: String?,

    @ColumnInfo(name = "projectDetails")
    var projectDetails: MutableList<ProjectDetailVO>,

//    @ColumnInfo(name = "references")
//    val references: ReferenceVO?,

    @ColumnInfo(name = "signature")
    var signature: String?,

    @PrimaryKey
    @ColumnInfo(name = "cvId")
    val cvId: Long,
        ){

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as CvVO
//
//        if (signature != null) {
//            if (other.signature == null) return false
//            if (!signature.contentEquals(other.signature)) return false
//        } else if (other.signature != null) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return signature?.contentHashCode() ?: 0
//    }

}