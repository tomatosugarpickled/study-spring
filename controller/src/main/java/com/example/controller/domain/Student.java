package com.example.controller.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class Student {
    private String name;
    private int kor;
    private int eng;
    private int math;

    public int getTotal(){
        return kor + eng + math;
    }

    public double getAverage(){
        return getTotal() / 3.0;
    }
}
