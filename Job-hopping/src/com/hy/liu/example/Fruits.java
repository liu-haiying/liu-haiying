package com.hy.liu.example;

import java.math.BigDecimal;

public class Fruits {

    private String name;

    private BigDecimal price;

    public Fruits(String name,BigDecimal price) {

        this.name = name;

        this.price = price;
    }

    public String isEat() {

        if (name.contains("毒")) {

            return name + "不能吃哦";
        }

        return name + "非常好吃";
    }
}
