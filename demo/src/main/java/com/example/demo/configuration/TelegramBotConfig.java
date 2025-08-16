package com.example.demo.configuration;

import com.example.demo.implementation.RecommendationBot;
import com.example.demo.service.RecommendationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Bean
    public TelegramBotsApi telegramBotsApi(RecommendationBot recommendationBot)
            throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(recommendationBot);
        return botsApi;
    }

    @Bean
    public RecommendationBot recommendationBot(UserService userService, RecommendationService recommendationService) {
        return new RecommendationBot(botToken, userService, recommendationService);
    }
}
