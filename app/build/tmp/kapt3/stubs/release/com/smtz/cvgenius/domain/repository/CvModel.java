package com.smtz.cvgenius.domain.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u0007H&J\u001a\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\tH&\u00a8\u0006\r"}, d2 = {"Lcom/smtz/cvgenius/domain/repository/CvModel;", "", "deleteCv", "", "cvId", "", "getAllCv", "Landroidx/lifecycle/LiveData;", "", "Lcom/smtz/cvgenius/domain/model/CvVO;", "getCv", "insertCV", "cvVO", "app_release"})
public abstract interface CvModel {
    
    public abstract void insertCV(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.CvVO cvVO);
    
    @org.jetbrains.annotations.Nullable
    public abstract androidx.lifecycle.LiveData<com.smtz.cvgenius.domain.model.CvVO> getCv(long cvId);
    
    @org.jetbrains.annotations.Nullable
    public abstract androidx.lifecycle.LiveData<java.util.List<com.smtz.cvgenius.domain.model.CvVO>> getAllCv();
    
    public abstract void deleteCv(long cvId);
}