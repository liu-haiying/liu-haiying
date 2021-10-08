package com.hy.liu.example;

import java.util.Arrays;

public class User {

    private String name;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {

        return this.name + this.age;
    }

    public static void main(String[] args) {

        String str = "42.202.134.224, 47.98.74.29";

        String[] arr = str.split(",");

        System.out.println(arr[0]);
    }
}
