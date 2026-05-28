package com.example.dependency.injection.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("desktop")
public class Desktop implements Computer {
    @Override
    public int getScreenWidth() {
        return 1920;
    }
}