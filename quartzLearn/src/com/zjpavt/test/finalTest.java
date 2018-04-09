package com.zjpavt.test;

public class finalTest {
    public static  void  main(String[] args){
    }
    public void test1(){
        final String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println(a.hashCode() + " " + b.hashCode());
        System.out.println(c.hashCode() + " " + e.hashCode());
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
