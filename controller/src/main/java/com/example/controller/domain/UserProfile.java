package com.example.controller.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfile {
    private Long    id;
    private String name;
    private String intro;
}
