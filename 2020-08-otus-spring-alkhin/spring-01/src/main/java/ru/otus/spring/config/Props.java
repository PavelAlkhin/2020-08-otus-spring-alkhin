package ru.otus.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "applicationru")
public class Props {

    @Getter @Setter
    private String filename;

    @Getter @Setter
    private Locale locale;

}
