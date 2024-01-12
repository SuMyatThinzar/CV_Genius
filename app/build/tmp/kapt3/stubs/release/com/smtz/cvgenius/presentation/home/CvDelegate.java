package com.smtz.cvgenius.presentation.home;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u000b"}, d2 = {"Lcom/smtz/cvgenius/presentation/home/CvDelegate;", "", "onTapCv", "", "cvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "onTapDeleteCv", "cvId", "", "onTapDuplicate", "onTapViewCv", "app_release"})
public abstract interface CvDelegate {
    
    public abstract void onTapCv(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.CvVO cvVO);
    
    public abstract void onTapViewCv(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.CvVO cvVO);
    
    public abstract void onTapDeleteCv(long cvId);
    
    public abstract void onTapDuplicate(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.CvVO cvVO);
}