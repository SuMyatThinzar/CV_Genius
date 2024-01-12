package com.smtz.cvgenius.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\tH\u0016J\u001a\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000bH\u0016\u00a8\u0006\u000f"}, d2 = {"Lcom/smtz/cvgenius/data/repository/CvModelImpl;", "Lcom/smtz/cvgenius/domain/repository/BaseModel;", "Lcom/smtz/cvgenius/domain/repository/CvModel;", "()V", "deleteCv", "", "cvId", "", "getAllCv", "Landroidx/lifecycle/LiveData;", "", "Lcom/smtz/cvgenius/domain/model/CvVO;", "getCv", "insertCV", "cvVO", "app_release"})
public final class CvModelImpl extends com.smtz.cvgenius.domain.repository.BaseModel implements com.smtz.cvgenius.domain.repository.CvModel {
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.data.repository.CvModelImpl INSTANCE = null;
    
    private CvModelImpl() {
        super();
    }
    
    @java.lang.Override
    public void insertCV(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.CvVO cvVO) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public androidx.lifecycle.LiveData<com.smtz.cvgenius.domain.model.CvVO> getCv(long cvId) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public androidx.lifecycle.LiveData<java.util.List<com.smtz.cvgenius.domain.model.CvVO>> getAllCv() {
        return null;
    }
    
    @java.lang.Override
    public void deleteCv(long cvId) {
    }
}