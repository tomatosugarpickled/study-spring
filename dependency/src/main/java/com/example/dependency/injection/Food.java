package com.example.dependency.injection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString @Getter
@RequiredArgsConstructor
public class Food {

    private final Knife knife;
}
