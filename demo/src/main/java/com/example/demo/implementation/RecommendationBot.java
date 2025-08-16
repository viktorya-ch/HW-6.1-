package com.example.demo.implementation;

import com.example.demo.model.DTO;
import com.example.demo.service.RecommendationService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

@Component
public class RecommendationBot extends TelegramLongPollingBot {
    private final String botUsername;
    private final UserService userService;
    private final RecommendationService recommendationService;
    private final BotMessageSender messageSender;

    public RecommendationBot(String botToken, UserService userService, RecommendationService recommendationService, BotMessageSender messageSender) {
        super();
        this.botUsername = " BankStarRecommendationBot ";
        this.userService = userService;
        this.recommendationService = recommendationService;
        this.messageSender = messageSender;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        Message message = update.getMessage();
        String text = message.getText();
        Long chatId = message.getChatId();
        if (text.equals("/start")) {
            messageSender.sendHelp(chatId);
        } else if (text.startsWith("/recommend")) {
            processRecommendCommand(chatId, text);
        } else {
            messageSender.sendMessage(chatId, " Неизвестная команда. Используйте: recommend <имя>");
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    private String processRecommendCommand(Long chatId, String text) {
        String[] parts = text.split(" ", 2);
        if (parts.length < 2) {
            messageSender.sendMessage(chatId, " Пожалуйста, укажите имя пользователя: / recommend <имя> ");
        }
        String username = parts[1].trim();
        List<User> users = userService.findUsersByName(username);

        if (users.isEmpty()) {
            messageSender.sendMessage(chatId, " Пользователь не найден ");
        } else if (users.size() > 1) {
            messageSender.sendMessage(chatId, " Найдено несколько пользователей. Уточните запрос ");
        } else {
            User user = users.get(0);
            List<DTO> recommendations = recommendationService.getRecommendations(user.getId().toString());
            messageSender.sendRecommendations(chatId, user, recommendations);
        }
        return username;
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public String getBotToken() {
        return "";
    }
}
