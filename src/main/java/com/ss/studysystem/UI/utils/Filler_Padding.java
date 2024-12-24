package com.ss.studysystem.UI.utils;

public enum Filler_Padding {
    XS(100),
    S(200),
    M(250),
    L(300),
    XL(350),
    XXL(450);

    private double size;
    private Filler_Padding(double size){
        this.size = size;
    }

    public double getSize() {
        return size;
    }
}
