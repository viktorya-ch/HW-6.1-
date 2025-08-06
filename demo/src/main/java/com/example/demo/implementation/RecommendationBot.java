package com.example.demo.implementation;

import com.example.demo.model.DTO;
import com.example.demo.service.RecommendationService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class RecommendationBot  extends TelegramLongPollingBot {
    private final String botUsername;
    private final UserService userService;
    private final RecommendationService recommendationService;

    public RecommendationBot(String botToken, UserService userService, RecommendationService recommendationService){
        super(botToken);
        this.botUsername = " BankStarRecommendationBot ";
        this.userService = userService;
        this.recommendationService = recommendationService;
    }

    @Override
    public void onUpdateReceived (Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        Message message = update.getMessage();
        String text = message.getText();
        Long chatId = message.getChatId();
        if (text.equals("/start")) {
            sendHelp(chatId);
        }else if (text.startsWith("/recommend")){
            processRecommendCommand(chatId,text);
        }else {sendMessage(chatId, " Неизвестная команда. Используйте: recommend <Ann_A_n_n>");
            return;
    }
        String username = parts[1].trim();
        List<User> users = userService.findUsersByName(username);

        if (users.isEmpty()){
            sendMessage(chatId, " Пользователь не найден ");
        }else if (users.size() > 1){
            sendMessage(chatId, " Найдено несколько пользователей. Уточните запрос ");
        }else{
            User user = users.get(0);
        List<DTO>recommendations = recommendationService.getRecommendations(user.getId().toString());
        sendRecommendations(chatId,user,recommendations);
        }
    }

    private void sendRecommendations(Long chatId, User user, List<DTO>recommendations){
    StringBuilder response = new StringBuilder();
    response.append(" Привет! ").append(user.getFullName()).append("!\n\n");
    if (recommendations.isEmpty()) {
        response.append(" На данный момент для вас нет рекомендаций ");
    }else {
        response.append(" Новые продукты для вас:\n\n");
        for (DTO rec:recommendations) {
            response.append(" ").append(rec.name()).append("\n");
            response.append(rec.text()).append("\n\n");
        }
    }
    sendMessage(chatId,response.toString());
    }

    private void sendHelp(Long chatId) {
        String helpText = " Добро пожаловать в банк Стар! \n\n" + " Для получения рекомендаций используйте команду : \n " + " /recommend <Имя Фамилия>\n\n " + " Например: /recommed <Александр Белов>";
        sendMessage(chatId,helpText);
    }
    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        }catch (TelegramApiException e)
        {
            //Обработка ошибки

    }
}
@Override
public String getBotUsername(){
    return botUsername;
    }
}
