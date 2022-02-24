package com.example.xixi.binfashizhan.chapter02;

/**
 * @author : xi-xi
 */
public class LoggingWidget extends Widget{

    @Override
    public synchronized void doSomething() {
        System.out.println(toString()+"：calling doSomething");
        super.doSomething();
    }
}
