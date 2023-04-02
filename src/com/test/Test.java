package com.test;

public class Test extends Main {
    public static void main(String[] args) {
        Base son = new Son();
        System.out.println(son.staticBase());
        System.out.println(son.normal());

    }
}
