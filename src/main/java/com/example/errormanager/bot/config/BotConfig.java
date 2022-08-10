package com.example.errormanager.bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Muhammadkomil Murodillayev, ср 09:56. 8/10/22
 */

@Getter
@Setter
@Configuration
@PropertySource(value = "application.yml")
public class BotConfig {

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

}
