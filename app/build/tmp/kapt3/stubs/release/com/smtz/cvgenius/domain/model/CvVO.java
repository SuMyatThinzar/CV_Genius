package com.smtz.cvgenius.domain.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b1\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\t\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\t\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\t\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\u0002\u0010\u001aJ\t\u0010=\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00160\tH\u00c6\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\t\u0010@\u001a\u00020\u0019H\u00c6\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000f\u0010C\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\u000f\u0010D\u001a\b\u0012\u0004\u0012\u00020\f0\tH\u00c6\u0003J\u000f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000e0\tH\u00c6\u0003J\u000f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00100\tH\u00c6\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\u000f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00140\tH\u00c6\u0003J\u00ad\u0001\u0010I\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\t2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\t2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u00c6\u0001J\u0013\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010M\u001a\u00020\u0003H\u00d6\u0001J\t\u0010N\u001a\u00020\u0012H\u00d6\u0001R$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0016\u0010\u0018\u001a\u00020\u00198\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR \u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001c\"\u0004\b0\u0010\u001eR$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001c\"\u0004\b2\u0010\u001eR \u0010\u0017\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010$\"\u0004\b4\u0010&R$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001c\"\u0004\b6\u0010\u001eR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u001c\"\u0004\b<\u0010\u001e\u00a8\u0006O"}, d2 = {"Lcom/smtz/cvgenius/domain/model/CvVO;", "", "templateId", "", "profileImage", "", "personalDetails", "Lcom/smtz/cvgenius/domain/model/PersonalDetailVO;", "educationDetails", "", "Lcom/smtz/cvgenius/domain/model/EducationDetailVO;", "workExperiences", "Lcom/smtz/cvgenius/domain/model/WorkExperienceVO;", "skills", "Lcom/smtz/cvgenius/domain/model/SkillsVO;", "achievements", "Lcom/smtz/cvgenius/domain/model/AchievementVO;", "objective", "", "projectDetails", "Lcom/smtz/cvgenius/domain/model/ProjectDetailVO;", "references", "Lcom/smtz/cvgenius/domain/model/ReferenceVO;", "signature", "cvId", "", "(I[BLcom/smtz/cvgenius/domain/model/PersonalDetailVO;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;J)V", "getAchievements", "()Ljava/util/List;", "setAchievements", "(Ljava/util/List;)V", "getCvId", "()J", "getEducationDetails", "setEducationDetails", "getObjective", "()Ljava/lang/String;", "setObjective", "(Ljava/lang/String;)V", "getPersonalDetails", "()Lcom/smtz/cvgenius/domain/model/PersonalDetailVO;", "setPersonalDetails", "(Lcom/smtz/cvgenius/domain/model/PersonalDetailVO;)V", "getProfileImage", "()[B", "setProfileImage", "([B)V", "getProjectDetails", "setProjectDetails", "getReferences", "setReferences", "getSignature", "setSignature", "getSkills", "setSkills", "getTemplateId", "()I", "setTemplateId", "(I)V", "getWorkExperiences", "setWorkExperiences", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_release"})
@androidx.room.Entity(tableName = "Cv")
@androidx.room.TypeConverters(value = {com.smtz.cvgenius.data.typeConverters.PersonalDetailTypeConverter.class, com.smtz.cvgenius.data.typeConverters.EducationDetailTypeConverter.class, com.smtz.cvgenius.data.typeConverters.WorkExperienceTypeConverter.class, com.smtz.cvgenius.data.typeConverters.SkillTypeConverter.class, com.smtz.cvgenius.data.typeConverters.AchievementTypeConverter.class, com.smtz.cvgenius.data.typeConverters.ProjectDetailTypeConverter.class, com.smtz.cvgenius.data.typeConverters.ReferenceTypeConverter.class, com.smtz.cvgenius.data.typeConverters.ProfileImageTypeConverter.class})
public final class CvVO {
    @androidx.room.ColumnInfo(name = "templateId")
    private int templateId;
    @androidx.room.ColumnInfo(name = "profileImage")
    @org.jetbrains.annotations.Nullable
    private byte[] profileImage;
    @androidx.room.ColumnInfo(name = "personalDetails")
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.PersonalDetailVO personalDetails;
    @androidx.room.ColumnInfo(name = "educationDetails")
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> educationDetails;
    @androidx.room.ColumnInfo(name = "workExperiences")
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> workExperiences;
    @androidx.room.ColumnInfo(name = "skills")
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.SkillsVO> skills;
    @androidx.room.ColumnInfo(name = "achievements")
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> achievements;
    @androidx.room.ColumnInfo(name = "objective")
    @org.jetbrains.annotations.Nullable
    private java.lang.String objective;
    @androidx.room.ColumnInfo(name = "projectDetails")
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.ProjectDetailVO> projectDetails;
    @androidx.room.ColumnInfo(name = "references")
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.ReferenceVO> references;
    @androidx.room.ColumnInfo(name = "signature")
    @org.jetbrains.annotations.Nullable
    private java.lang.String signature;
    @androidx.room.PrimaryKey
    @androidx.room.ColumnInfo(name = "cvId")
    private final long cvId = 0L;
    
    public CvVO(int templateId, @org.jetbrains.annotations.Nullable
    byte[] profileImage, @org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.PersonalDetailVO personalDetails, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> educationDetails, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> workExperiences, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.SkillsVO> skills, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> achievements, @org.jetbrains.annotations.Nullable
    java.lang.String objective, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.ProjectDetailVO> projectDetails, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.ReferenceVO> references, @org.jetbrains.annotations.Nullable
    java.lang.String signature, long cvId) {
        super();
    }
    
    public final int getTemplateId() {
        return 0;
    }
    
    public final void setTemplateId(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final byte[] getProfileImage() {
        return null;
    }
    
    public final void setProfileImage(@org.jetbrains.annotations.Nullable
    byte[] p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.smtz.cvgenius.domain.model.PersonalDetailVO getPersonalDetails() {
        return null;
    }
    
    public final void setPersonalDetails(@org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.PersonalDetailVO p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> getEducationDetails() {
        return null;
    }
    
    public final void setEducationDetails(@org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> getWorkExperiences() {
        return null;
    }
    
    public final void setWorkExperiences(@org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.SkillsVO> getSkills() {
        return null;
    }
    
    public final void setSkills(@org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.SkillsVO> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> getAchievements() {
        return null;
    }
    
    public final void setAchievements(@org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getObjective() {
        return null;
    }
    
    public final void setObjective(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.ProjectDetailVO> getProjectDetails() {
        return null;
    }
    
    public final void setProjectDetails(@org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.ProjectDetailVO> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.ReferenceVO> getReferences() {
        return null;
    }
    
    public final void setReferences(@org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.ReferenceVO> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSignature() {
        return null;
    }
    
    public final void setSignature(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final long getCvId() {
        return 0L;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.ReferenceVO> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    public final long component12() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final byte[] component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.smtz.cvgenius.domain.model.PersonalDetailVO component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.SkillsVO> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.smtz.cvgenius.domain.model.ProjectDetailVO> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.smtz.cvgenius.domain.model.CvVO copy(int templateId, @org.jetbrains.annotations.Nullable
    byte[] profileImage, @org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.PersonalDetailVO personalDetails, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> educationDetails, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> workExperiences, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.SkillsVO> skills, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> achievements, @org.jetbrains.annotations.Nullable
    java.lang.String objective, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.ProjectDetailVO> projectDetails, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.ReferenceVO> references, @org.jetbrains.annotations.Nullable
    java.lang.String signature, long cvId) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}