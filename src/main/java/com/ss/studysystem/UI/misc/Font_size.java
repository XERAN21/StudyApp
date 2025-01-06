package com.ss.studysystem.UI.misc;

public enum Font_size {
    XS(8),
    S(12),
    M(16),
    L(20),
    XL(24),
    XXL(28);

    private double size;
    private Font_size(double size){
        this.size = size;
    }

    public double getSize() {
        return size;
    }
}
