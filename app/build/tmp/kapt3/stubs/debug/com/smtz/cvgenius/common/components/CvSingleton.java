package com.smtz.cvgenius.common.components;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2 = {"Lcom/smtz/cvgenius/common/components/CvSingleton;", "", "()V", "cvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "getCvVO", "()Lcom/smtz/cvgenius/domain/model/CvVO;", "setCvVO", "(Lcom/smtz/cvgenius/domain/model/CvVO;)V", "cleanup", "", "Companion", "Holder", "app_debug"})
public final class CvSingleton {
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO cvVO;
    @org.jetbrains.annotations.NotNull
    private static final kotlin.Lazy<?> instance$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.common.components.CvSingleton.Companion Companion = null;
    
    private CvSingleton() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.smtz.cvgenius.domain.model.CvVO getCvVO() {
        return null;
    }
    
    public final void setCvVO(@org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.CvVO p0) {
    }
    
    public final void cleanup() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/smtz/cvgenius/common/components/CvSingleton$Companion;", "", "()V", "instance", "Lcom/smtz/cvgenius/common/components/CvSingleton;", "getInstance", "()Lcom/smtz/cvgenius/common/components/CvSingleton;", "instance$delegate", "Lkotlin/Lazy;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.smtz.cvgenius.common.components.CvSingleton getInstance() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\n\n\u0002\b\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\b"}, d2 = {"Lcom/smtz/cvgenius/common/components/CvSingleton$Holder;", "", "()V", "INSTANCE", "Lcom/smtz/cvgenius/common/components/CvSingleton;", "getINSTANCE", "()Lcom/smtz/cvgenius/common/components/CvSingleton;", "INSTANCE$1", "app_debug"})
    static final class Holder {
        @org.jetbrains.annotations.NotNull
        private static final com.smtz.cvgenius.common.components.CvSingleton INSTANCE$1 = null;
        @org.jetbrains.annotations.NotNull
        public static final com.smtz.cvgenius.common.components.CvSingleton.Holder INSTANCE = null;
        
        private Holder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.smtz.cvgenius.common.components.CvSingleton getINSTANCE() {
            return null;
        }
    }
}