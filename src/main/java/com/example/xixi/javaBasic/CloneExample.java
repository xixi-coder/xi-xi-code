package com.example.xixi.javaBasic;

/**
 * @author : xi-xi
 */
public class CloneExample  implements Cloneable{
    private int a;
    private int b;


    @Override
    protected CloneExample clone() throws CloneNotSupportedException {
        return (CloneExample)super.clone();
    }
}
