package edu.java.bot;

import edu.java.bot.botClass.TgBot;
import edu.java.bot.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {


    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
        TgBot bot = new TgBot("6918381858:AAEXd5FklSbMO8cu-4Rhw9V3oYbHuIQCfGM");
        bot.start();
    }

}
