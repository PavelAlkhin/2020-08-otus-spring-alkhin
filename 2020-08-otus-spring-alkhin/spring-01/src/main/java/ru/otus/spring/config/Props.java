package ru.otus.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "applicationru")
@Getter @Setter
public class Props {

    private String filename;

    private Locale locale;

}
