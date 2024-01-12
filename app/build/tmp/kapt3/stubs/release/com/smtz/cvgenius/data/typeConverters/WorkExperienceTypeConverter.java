package com.smtz.cvgenius.data.typeConverters;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0007J\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0007\u00a8\u0006\n"}, d2 = {"Lcom/smtz/cvgenius/data/typeConverters/WorkExperienceTypeConverter;", "", "()V", "toString", "", "workExperiences", "", "Lcom/smtz/cvgenius/domain/model/WorkExperienceVO;", "toWorkExperienceVO", "workExperienceJsonString", "app_release"})
public final class WorkExperienceTypeConverter {
    
    public WorkExperienceTypeConverter() {
        super();
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String toString(@org.jetbrains.annotations.Nullable
    java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> workExperiences) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> toWorkExperienceVO(@org.jetbrains.annotations.NotNull
    java.lang.String workExperienceJsonString) {
        return null;
    }
}