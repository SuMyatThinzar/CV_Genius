package com.smtz.cvgenius.data.typeConverters;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007\u00a8\u0006\t"}, d2 = {"Lcom/smtz/cvgenius/data/typeConverters/PersonalDetailTypeConverter;", "", "()V", "toPersonalDetailVO", "Lcom/smtz/cvgenius/domain/model/PersonalDetailVO;", "str", "", "toString", "personalDetail", "app_debug"})
public final class PersonalDetailTypeConverter {
    
    public PersonalDetailTypeConverter() {
        super();
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String toString(@org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.PersonalDetailVO personalDetail) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final com.smtz.cvgenius.domain.model.PersonalDetailVO toPersonalDetailVO(@org.jetbrains.annotations.NotNull
    java.lang.String str) {
        return null;
    }
}