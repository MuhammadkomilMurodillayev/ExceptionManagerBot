package com.example.errormanager.api.util;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("application.yml")
public class RootUtil {

    @Value(value = "${file.root}")
    private String root;
}
