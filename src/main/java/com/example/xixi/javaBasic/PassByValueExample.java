package com.example.xixi.javaBasic;

import java.util.HashSet;

/**
 * @author : xi-xi
 */

public class PassByValueExample {
    public static void main(String[] args) {
//        Dog dog = new Dog("A");
//        System.out.println(dog.getObjectAddress()); // Dog@4554617c
//        func(dog);
//        System.out.println(dog.getObjectAddress()); // Dog@4554617c
//        System.out.println(dog.getName());          // A
//        InterfaceExample ie2 = new InterfaceImplementExample();
//        ie2.func2();
//        System.out.println(InterfaceExample.z);
//
//        SuperExtendExample superExtendExample = new SuperExtendExample(1, 2, 3);
//        superExtendExample.func();


//        EqualExample e1 = new EqualExample(1, 1, 1);
//        EqualExample e2 = new EqualExample(1, 1, 1);
//        System.out.println(e1.equals(e2)); // true
//        HashSet<EqualExample> set = new HashSet<>();
//        set.add(e1);
//        set.add(e2);
//        System.out.println(set.size());   // 2
//        ToStringExample example = new ToStringExample(123);
//        System.out.println(example.toString());
//        CloneExample e1 = new CloneExample();
//        try {
//            CloneExample e2 = e1.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//浅拷贝
//        ShallowCloneExample e1 = new ShallowCloneExample();
//        ShallowCloneExample e2 = null;
//        try {
//            e2 = e1.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        e1.set(2, 222);
//        System.out.println(e2.get(2)); // 222
//深拷贝
//        DeepCloneExample e1 = new DeepCloneExample();
//        DeepCloneExample e2 = null;
//        try {
//            e2 = e1.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        e1.set(2, 222);
//        System.out.println(e2.get(2)); // 2

        CloneConstructorExample e1 = new CloneConstructorExample();
        CloneConstructorExample e2 = new CloneConstructorExample(e1);
        e1.set(2, 222);
        System.out.println(e2.get(2)); // 2

    }

    private static void func(Dog dog) {
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        dog = new Dog("B");
        System.out.println(dog.getObjectAddress()); // Dog@74a14482
        System.out.println(dog.getName());          // B
    }
}
