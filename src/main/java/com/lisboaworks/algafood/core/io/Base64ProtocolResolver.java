package com.lisboaworks.algafood.core.io;

import com.mysql.cj.util.Base64Decoder;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Base64;

public class Base64ProtocolResolver implements ProtocolResolver, ApplicationListener<ApplicationContextInitializedEvent> {

    private static final int BASE_64_STRING_START_INDEX = 7;

    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (location.startsWith("base64:")) {
            byte[] decodedResource = Base64.getDecoder().decode(location.substring(BASE_64_STRING_START_INDEX));
            return new ByteArrayResource(decodedResource);
        }

        return null;
    }

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        event.getApplicationContext().addProtocolResolver(this);
    }
}
