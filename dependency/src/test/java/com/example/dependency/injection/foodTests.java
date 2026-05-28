package com.example.dependency.injection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j

//@SpringBootTest

public class foodTests {
//    @Autowired
//    private Knife knife;

    @Test
    public void foodTest(){

        Knife knife = new Knife();
        Food food = new Food(knife);

        log.info("{}",food);

//        Assertions.assertThat(knife).isNull();
    }
}
