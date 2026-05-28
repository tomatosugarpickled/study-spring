package com.example.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CropCheckResponse {
    @JsonProperty("yield_kg_per_m2")
    private Double yieldKgPerM2;
}
