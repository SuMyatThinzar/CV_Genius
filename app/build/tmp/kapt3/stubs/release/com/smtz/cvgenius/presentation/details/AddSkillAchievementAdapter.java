package com.smtz.cvgenius.presentation.details;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\"\u0010\u0017\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/AddSkillAchievementAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/smtz/cvgenius/presentation/details/AddSkillAchievementViewHolder;", "delegate", "Lcom/smtz/cvgenius/presentation/details/ButtonSaveSkillAchievementDelegate;", "skillAchiObjSign", "", "(Lcom/smtz/cvgenius/presentation/details/ButtonSaveSkillAchievementDelegate;Ljava/lang/String;)V", "mAchievementList", "", "Lcom/smtz/cvgenius/domain/model/AchievementVO;", "mSkillsList", "Lcom/smtz/cvgenius/domain/model/SkillsVO;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setNewData", "skillList", "achievementList", "app_release"})
public final class AddSkillAchievementAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.smtz.cvgenius.presentation.details.AddSkillAchievementViewHolder> {
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.presentation.details.ButtonSaveSkillAchievementDelegate delegate;
    @org.jetbrains.annotations.NotNull
    private java.lang.String skillAchiObjSign;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.SkillsVO> mSkillsList;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> mAchievementList;
    
    public AddSkillAchievementAdapter(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.presentation.details.ButtonSaveSkillAchievementDelegate delegate, @org.jetbrains.annotations.NotNull
    java.lang.String skillAchiObjSign) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.smtz.cvgenius.presentation.details.AddSkillAchievementViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.presentation.details.AddSkillAchievementViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void setNewData(@org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.SkillsVO> skillList, @org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> achievementList) {
    }
}