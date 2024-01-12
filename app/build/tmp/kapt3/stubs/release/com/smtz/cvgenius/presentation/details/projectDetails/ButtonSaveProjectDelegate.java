package com.smtz.cvgenius.presentation.details.projectDetails;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/projectDetails/ButtonSaveProjectDelegate;", "", "onTapDelete", "", "id", "", "onTapSaveProject", "projectDetailVO", "Lcom/smtz/cvgenius/domain/model/ProjectDetailVO;", "app_release"})
public abstract interface ButtonSaveProjectDelegate {
    
    public abstract void onTapSaveProject(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.ProjectDetailVO projectDetailVO);
    
    public abstract void onTapDelete(long id);
}