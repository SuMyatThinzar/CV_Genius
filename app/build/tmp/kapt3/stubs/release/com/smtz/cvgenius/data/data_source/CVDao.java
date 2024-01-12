package com.smtz.cvgenius.data.data_source;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0016\u0010\u0006\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u0007H\'J\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\tH\'\u00a8\u0006\r"}, d2 = {"Lcom/smtz/cvgenius/data/data_source/CVDao;", "", "deleteCV", "", "cvId", "", "getAllCV", "Landroidx/lifecycle/LiveData;", "", "Lcom/smtz/cvgenius/domain/model/CvVO;", "getSingleCV", "insertNewCV", "cvVO", "app_release"})
@androidx.room.Dao
public abstract interface CVDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertNewCV(@org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.CvVO cvVO);
    
    @androidx.room.Query(value = "SELECT * FROM Cv WHERE cvId = :cvId")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<com.smtz.cvgenius.domain.model.CvVO> getSingleCV(long cvId);
    
    @androidx.room.Query(value = "SELECT * FROM Cv")
    @org.jetbrains.annotations.Nullable
    public abstract androidx.lifecycle.LiveData<java.util.List<com.smtz.cvgenius.domain.model.CvVO>> getAllCV();
    
    @androidx.room.Query(value = "DELETE FROM Cv WHERE cvId = :cvId")
    public abstract void deleteCV(long cvId);
}