package com.smtz.cvgenius.presentation.preview.templateViewPods;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J.\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u001bH\u0002J:\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020\u00132\b\u0010&\u001a\u0004\u0018\u00010 H\u0002J\b\u0010\'\u001a\u00020\u001bH\u0002J\b\u0010(\u001a\u00020\u001bH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/smtz/cvgenius/presentation/preview/templateViewPods/ResumeSecondThreeViewPod;", "Lcom/smtz/cvgenius/presentation/preview/templateViewPods/BaseViewPod;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "binding", "Lcom/smtz/cvgenius/databinding/ViewPodZresumeSecondThreeBinding;", "containerWorkExp", "Landroid/widget/LinearLayout;", "mCvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "mWorkExperienceList", "", "Lcom/smtz/cvgenius/domain/model/WorkExperienceVO;", "pageHeight", "", "pageIndex", "", "parentFullHeight", "generatePageBitmaps", "Landroid/graphics/Bitmap;", "viewPodLayout", "Landroid/view/ViewGroup;", "blankSpaceHeight", "onFinishInflate", "", "separatePages", "setLayouts", "Landroid/widget/TextView;", "text", "", "textSize", "", "fontFamily", "margin", "textColor", "bold", "setUpData", "setUpWorkExp", "app_debug"})
public final class ResumeSecondThreeViewPod extends com.smtz.cvgenius.presentation.preview.templateViewPods.BaseViewPod {
    private com.smtz.cvgenius.databinding.ViewPodZresumeSecondThreeBinding binding;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.WorkExperienceVO> mWorkExperienceList;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mCvVO;
    private android.widget.LinearLayout containerWorkExp;
    private int pageIndex = 0;
    private double pageHeight = 1374.0;
    private int parentFullHeight = 0;
    
    @kotlin.jvm.JvmOverloads
    public ResumeSecondThreeViewPod(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null, null);
    }
    
    @java.lang.Override
    protected void onFinishInflate() {
    }
    
    private final void setUpData() {
    }
    
    private final void setUpWorkExp() {
    }
    
    private final void separatePages() {
    }
    
    private final java.util.List<android.graphics.Bitmap> generatePageBitmaps(android.content.Context context, android.view.ViewGroup viewPodLayout, int pageHeight, int blankSpaceHeight) {
        return null;
    }
    
    private final android.widget.TextView setLayouts(java.lang.String text, float textSize, int fontFamily, java.lang.String margin, int textColor, java.lang.String bold) {
        return null;
    }
    
    @kotlin.jvm.JvmOverloads
    public ResumeSecondThreeViewPod(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null, null);
    }
}