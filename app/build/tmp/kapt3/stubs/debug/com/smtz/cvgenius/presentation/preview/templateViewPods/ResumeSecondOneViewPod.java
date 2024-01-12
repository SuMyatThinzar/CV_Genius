package com.smtz.cvgenius.presentation.preview.templateViewPods;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001bH\u0014J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0013H\u0016J:\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\'2\b\u0010*\u001a\u0004\u0018\u00010$H\u0002J\b\u0010+\u001a\u00020\u001bH\u0002J\u0018\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\'2\u0006\u0010.\u001a\u00020\nH\u0002J\b\u0010/\u001a\u00020\u001bH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/smtz/cvgenius/presentation/preview/templateViewPods/ResumeSecondOneViewPod;", "Lcom/smtz/cvgenius/presentation/preview/templateViewPods/BaseViewPod;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "binding", "Lcom/smtz/cvgenius/databinding/ViewPodZresumeSecondOneBinding;", "containerFirstPageLeftSide", "Landroid/widget/LinearLayout;", "containerFirstPageRightSide", "containerSecondPageLeftSide", "containerSecondPageRightSide", "containerWorkExp", "contentList", "", "Landroid/view/View;", "isInitialSetupDone", "", "isSentToSecondPage", "mCvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "mWorkExperienceList", "", "Lcom/smtz/cvgenius/domain/model/WorkExperienceVO;", "addContentToPages", "", "getCurrentPageHeight", "", "onFinishInflate", "onWindowFocusChanged", "hasFocus", "setLayouts", "Landroid/widget/TextView;", "text", "", "textSize", "fontFamily", "", "margin", "textColor", "bold", "setUpData", "setUpTheChildViews", "pageNumber", "childLayout", "setUpWorkExp", "app_debug"})
public final class ResumeSecondOneViewPod extends com.smtz.cvgenius.presentation.preview.templateViewPods.BaseViewPod {
    private com.smtz.cvgenius.databinding.ViewPodZresumeSecondOneBinding binding;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> mWorkExperienceList;
    private android.widget.LinearLayout containerWorkExp;
    private android.widget.LinearLayout containerFirstPageRightSide;
    private android.widget.LinearLayout containerFirstPageLeftSide;
    private android.widget.LinearLayout containerSecondPageRightSide;
    private android.widget.LinearLayout containerSecondPageLeftSide;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mCvVO;
    private boolean isInitialSetupDone = false;
    private boolean isSentToSecondPage = false;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<android.view.View> contentList = null;
    
    public ResumeSecondOneViewPod(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null, null);
    }
    
    @java.lang.Override
    protected void onFinishInflate() {
    }
    
    @java.lang.Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }
    
    private final void setUpData() {
    }
    
    private final void setUpWorkExp() {
    }
    
    private final void addContentToPages() {
    }
    
    private final float getCurrentPageHeight() {
        return 0.0F;
    }
    
    private final void setUpTheChildViews(int pageNumber, android.widget.LinearLayout childLayout) {
    }
    
    private final android.widget.TextView setLayouts(java.lang.String text, float textSize, int fontFamily, java.lang.String margin, int textColor, java.lang.String bold) {
        return null;
    }
}