package com.example.dependency.injection.qualifier;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Primary
public class Outback implements Restaurant {
    private int steakPrice;

    public Outback() {
        this.steakPrice = Restaurant.price - 10000;
    }

    @Override
    public boolean isSaladBar() {
        return false;
    }
}
