package com.example.controller.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
public class School {
    private Long id;
    private String name;

    private List<Student> students;
}
