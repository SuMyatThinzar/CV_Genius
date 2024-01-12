package com.smtz.cvgenius.data.data_source;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/smtz/cvgenius/data/data_source/CVGeniusDatabase;", "Landroidx/room/RoomDatabase;", "()V", "cvDao", "Lcom/smtz/cvgenius/data/data_source/CVDao;", "Companion", "app_release"})
@androidx.room.Database(entities = {com.smtz.cvgenius.domain.model.CvVO.class}, version = 17, exportSchema = false)
public abstract class CVGeniusDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DB_NAME = "CVGenius";
    @org.jetbrains.annotations.Nullable
    private static com.smtz.cvgenius.data.data_source.CVGeniusDatabase dbInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.data.data_source.CVGeniusDatabase.Companion Companion = null;
    
    public CVGeniusDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.smtz.cvgenius.data.data_source.CVDao cvDao();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000e"}, d2 = {"Lcom/smtz/cvgenius/data/data_source/CVGeniusDatabase$Companion;", "", "()V", "DB_NAME", "", "dbInstance", "Lcom/smtz/cvgenius/data/data_source/CVGeniusDatabase;", "getDbInstance", "()Lcom/smtz/cvgenius/data/data_source/CVGeniusDatabase;", "setDbInstance", "(Lcom/smtz/cvgenius/data/data_source/CVGeniusDatabase;)V", "getDBInstance", "context", "Landroid/content/Context;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.smtz.cvgenius.data.data_source.CVGeniusDatabase getDbInstance() {
            return null;
        }
        
        public final void setDbInstance(@org.jetbrains.annotations.Nullable
        com.smtz.cvgenius.data.data_source.CVGeniusDatabase p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.smtz.cvgenius.data.data_source.CVGeniusDatabase getDBInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}