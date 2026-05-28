package com.app.threetier.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FileContentType {
    IMAGE("image"), OTHER("other");

    private String value;

    private static final Map<String, FileContentType> FILE_CONTENT_TYPE_MAP =
            Arrays.stream(FileContentType.values()).collect(Collectors.toMap(FileContentType::getValue, Function.identity()));

    FileContentType(String value) {
        this.value = value;
    }

    public static FileContentType getFileContentType(String value) {
        return FILE_CONTENT_TYPE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }


}
