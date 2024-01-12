package com.smtz.cvgenius.domain.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2 = {"Lcom/smtz/cvgenius/domain/repository/BaseModel;", "", "()V", "mCVGeniusDatabase", "Lcom/smtz/cvgenius/data/data_source/CVGeniusDatabase;", "getMCVGeniusDatabase", "()Lcom/smtz/cvgenius/data/data_source/CVGeniusDatabase;", "setMCVGeniusDatabase", "(Lcom/smtz/cvgenius/data/data_source/CVGeniusDatabase;)V", "initDatabase", "", "context", "Landroid/content/Context;", "app_debug"})
public abstract class BaseModel {
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.data.data_source.CVGeniusDatabase mCVGeniusDatabase;
    
    public BaseModel() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    protected final com.smtz.cvgenius.data.data_source.CVGeniusDatabase getMCVGeniusDatabase() {
        return null;
    }
    
    protected final void setMCVGeniusDatabase(@org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.data.data_source.CVGeniusDatabase p0) {
    }
    
    public final void initDatabase(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
}