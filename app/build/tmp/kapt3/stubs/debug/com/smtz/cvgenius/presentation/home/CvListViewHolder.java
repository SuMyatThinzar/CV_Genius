package com.smtz.cvgenius.presentation.home;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/smtz/cvgenius/presentation/home/CvListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/smtz/cvgenius/databinding/ViewHolderCvListBinding;", "delegate", "Lcom/smtz/cvgenius/presentation/home/CvDelegate;", "(Lcom/smtz/cvgenius/databinding/ViewHolderCvListBinding;Lcom/smtz/cvgenius/presentation/home/CvDelegate;)V", "mDataVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "bindData", "", "data", "app_debug"})
public final class CvListViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.databinding.ViewHolderCvListBinding binding;
    @org.jetbrains.annotations.NotNull
    private final com.smtz.cvgenius.presentation.home.CvDelegate delegate = null;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mDataVO;
    
    public CvListViewHolder(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.databinding.ViewHolderCvListBinding binding, @org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.presentation.home.CvDelegate delegate) {
        super(null);
    }
    
    public final void bindData(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.CvVO data) {
    }
}