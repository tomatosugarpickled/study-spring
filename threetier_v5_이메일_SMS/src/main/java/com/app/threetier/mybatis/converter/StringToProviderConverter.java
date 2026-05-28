package com.app.threetier.mybatis.converter;

import com.app.threetier.common.enumeration.Provider;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProviderConverter implements Converter<String, Provider> {
    @Override
    public Provider convert(String source) {
        return Provider.getProvider(source);
    }
}
