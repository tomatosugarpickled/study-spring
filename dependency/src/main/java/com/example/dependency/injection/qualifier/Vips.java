package com.example.dependency.injection.qualifier;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Qualifier("vips")
public class Vips implements Restaurant {
    private int steakPrice;

    public Vips() {
        this.steakPrice = Restaurant.price + 60000;
    }

    @Override
    public boolean isSaladBar() {
        return true;
    }
}














