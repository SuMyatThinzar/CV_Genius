package com.smtz.cvgenius.domain.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/smtz/cvgenius/domain/model/SkillsVO;", "", "skill", "", "id", "", "(Ljava/lang/String;J)V", "getId", "()J", "getSkill", "()Ljava/lang/String;", "app_debug"})
@androidx.room.Entity(tableName = "skill")
public final class SkillsVO {
    @androidx.room.ColumnInfo(name = "skill")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String skill = null;
    @androidx.room.PrimaryKey
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    
    public SkillsVO(@org.jetbrains.annotations.NotNull
    java.lang.String skill, long id) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSkill() {
        return null;
    }
    
    public final long getId() {
        return 0L;
    }
}