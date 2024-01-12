package com.smtz.cvgenius.presentation.details.educationDetails;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0014\u0010\u0013\u001a\u00020\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/educationDetails/AddEducationDetailsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/smtz/cvgenius/presentation/details/educationDetails/AddEducationDetailViewHolder;", "delegate", "Lcom/smtz/cvgenius/presentation/details/educationDetails/ButtonSaveEducationDelegate;", "(Lcom/smtz/cvgenius/presentation/details/educationDetails/ButtonSaveEducationDelegate;)V", "mData", "", "Lcom/smtz/cvgenius/domain/model/EducationDetailVO;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setNewData", "data", "app_debug"})
public final class AddEducationDetailsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.smtz.cvgenius.presentation.details.educationDetails.AddEducationDetailViewHolder> {
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.presentation.details.educationDetails.ButtonSaveEducationDelegate delegate;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> mData;
    
    public AddEducationDetailsAdapter(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.presentation.details.educationDetails.ButtonSaveEducationDelegate delegate) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.smtz.cvgenius.presentation.details.educationDetails.AddEducationDetailViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.presentation.details.educationDetails.AddEducationDetailViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void setNewData(@org.jetbrains.annotations.NotNull
    java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> data) {
    }
}