package com.hy.liu.example;

public class MyDemo {

    public static void main(String[] args) {

        User user = new User();

        user.setAge(123);

        user.setName("Sc");

        System.out.println("Before change User --->" + user);

        change(user);

        System.out.println("name:" + user.getName());

        System.out.println("age:" + user.getAge());

        System.out.println("After change User --->" + user);
    }

    public static void change(User user) {

        user.setName("Cs");

        user.setAge(6);
    }
}
