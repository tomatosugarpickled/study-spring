package com.app.threetier.mybatis.converter;

import com.app.threetier.common.enumeration.FileContentType;
import com.app.threetier.common.enumeration.Provider;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFileContentTypeConverter implements Converter<String, FileContentType> {
    @Override
    public FileContentType convert(String source) {
        return FileContentType.getFileContentType(source);
    }
}
