package com.example.dependency.injection.qualifier;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Slf4j
public class QualifierTests {
    @Autowired
//    @Qualifier("laptop")
    private Computer laptop;

    @Autowired @Qualifier("desktop")
    private Computer desktop;

    @Autowired
    private Restaurant outback;

    @Autowired @Qualifier("vips")
    private Restaurant vips;

    @Test
    public void laptopTest(){
//        assertNotNull(laptop);
        assertNull(laptop);
    }

    @Test
    public void DesktopTest(){
//        assertNotNull(desktop);
        assertNull(desktop);
    }

    @Test
    public void outbackTest(){
        assertNull(outback);
//        assertNotNull(outback);
    }

    @Test
    public void vipsTest(){
        assertNull(vips);
//        assertNotNull(vips);
    }

}











