package com.example.controller.domain;

import lombok.Data;

import java.util.List;

@Data
public class RecommendResponse {
    private List<UserProfile> users;
}
