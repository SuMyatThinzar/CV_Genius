package com.smtz.cvgenius.presentation.details;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001c\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&\u00a8\u0006\r"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/ButtonSaveSkillAchievementDelegate;", "", "onTapDelete", "", "id", "", "skillOrAchievement", "", "onTapSave", "skillsVO", "Lcom/smtz/cvgenius/domain/model/SkillsVO;", "achievementVO", "Lcom/smtz/cvgenius/domain/model/AchievementVO;", "app_debug"})
public abstract interface ButtonSaveSkillAchievementDelegate {
    
    public abstract void onTapSave(@org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.SkillsVO skillsVO, @org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.AchievementVO achievementVO);
    
    public abstract void onTapDelete(long id, @org.jetbrains.annotations.NotNull
    java.lang.String skillOrAchievement);
}