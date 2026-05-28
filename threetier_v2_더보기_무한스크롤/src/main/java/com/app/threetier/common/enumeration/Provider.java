package com.app.threetier.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Provider {
    THREETIER("threetier"), KAKAO("kakao");

    private String value;

    private static final Map<String, Provider> STATUS_MAP =
            Arrays.stream(Provider.values()).collect(Collectors.toMap(Provider::getValue, Function.identity()));

    Provider(String value) {
        this.value = value;
    }

    public static Provider getProvider(String value) {
        return STATUS_MAP.get(value);
    }

    public String getValue() {
        return value;
    }


}
