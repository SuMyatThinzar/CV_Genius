package com.smtz.cvgenius.domain.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\f\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u0010\u00a8\u0006\u0016"}, d2 = {"Lcom/smtz/cvgenius/domain/model/WorkExperienceVO;", "", "position", "", "company", "description", "startDate", "endDate", "id", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getCompany", "()Ljava/lang/String;", "getDescription", "getEndDate", "setEndDate", "(Ljava/lang/String;)V", "getId", "()J", "getPosition", "getStartDate", "setStartDate", "app_release"})
@androidx.room.Entity(tableName = "workExperience")
public final class WorkExperienceVO {
    @androidx.room.ColumnInfo(name = "position")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String position = null;
    @androidx.room.ColumnInfo(name = "company")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String company = null;
    @androidx.room.ColumnInfo(name = "description")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String description = null;
    @androidx.room.ColumnInfo(name = "startDate")
    @org.jetbrains.annotations.Nullable
    private java.lang.String startDate;
    @androidx.room.ColumnInfo(name = "endDate")
    @org.jetbrains.annotations.Nullable
    private java.lang.String endDate;
    @androidx.room.PrimaryKey
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    
    public WorkExperienceVO(@org.jetbrains.annotations.NotNull
    java.lang.String position, @org.jetbrains.annotations.NotNull
    java.lang.String company, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String startDate, @org.jetbrains.annotations.Nullable
    java.lang.String endDate, long id) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPosition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCompany() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStartDate() {
        return null;
    }
    
    public final void setStartDate(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEndDate() {
        return null;
    }
    
    public final void setEndDate(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final long getId() {
        return 0L;
    }
}