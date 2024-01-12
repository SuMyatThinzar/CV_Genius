package com.smtz.cvgenius.presentation.details.reference;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0014J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0011H\u0002J\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0015J$\u0010!\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020%H\u0002J\b\u0010\'\u001a\u00020\u001bH\u0002J\u0016\u0010(\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u000fR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/reference/ReferenceViewPod;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/smtz/cvgenius/databinding/ViewPodReferencesBinding;", "companyName", "", "emailAddress", "isExpanded", "", "mDelegate", "Lcom/smtz/cvgenius/presentation/details/reference/ButtonSaveReferenceDelegate;", "mId", "", "mReferenceVO", "Lcom/smtz/cvgenius/domain/model/ReferenceVO;", "others", "phoneNumber", "position", "referenceName", "bindData", "", "onFinishInflate", "setDelegate", "delegate", "setUpData", "referenceVO", "setUpError", "editText", "Landroid/widget/EditText;", "textView", "Landroid/widget/TextView;", "error", "setUpListeners", "setUpReferenceViewPod", "changeBtnAdd", "app_release"})
public final class ReferenceViewPod extends androidx.cardview.widget.CardView {
    private com.smtz.cvgenius.databinding.ViewPodReferencesBinding binding;
    private com.smtz.cvgenius.presentation.details.reference.ButtonSaveReferenceDelegate mDelegate;
    @org.jetbrains.annotations.NotNull
    private java.lang.String referenceName = "";
    @org.jetbrains.annotations.Nullable
    private java.lang.String position;
    @org.jetbrains.annotations.Nullable
    private java.lang.String companyName;
    @org.jetbrains.annotations.Nullable
    private java.lang.String emailAddress;
    @org.jetbrains.annotations.Nullable
    private java.lang.String phoneNumber;
    @org.jetbrains.annotations.Nullable
    private java.lang.String others;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.ReferenceVO mReferenceVO;
    private long mId;
    private boolean isExpanded = false;
    
    @kotlin.jvm.JvmOverloads
    public ReferenceViewPod(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @java.lang.Override
    protected void onFinishInflate() {
    }
    
    public final void setUpReferenceViewPod(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.presentation.details.reference.ButtonSaveReferenceDelegate delegate, boolean changeBtnAdd) {
    }
    
    public final void setUpData(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.ReferenceVO referenceVO) {
    }
    
    private final void setDelegate(com.smtz.cvgenius.presentation.details.reference.ButtonSaveReferenceDelegate delegate) {
    }
    
    private final void bindData() {
    }
    
    private final void setUpListeners() {
    }
    
    private final void setUpError(android.widget.EditText editText, android.widget.TextView textView, android.widget.TextView error) {
    }
    
    @kotlin.jvm.JvmOverloads
    public ReferenceViewPod(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads
    public ReferenceViewPod(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null);
    }
}