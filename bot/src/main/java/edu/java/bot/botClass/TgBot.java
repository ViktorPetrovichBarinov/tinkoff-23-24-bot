package edu.java.bot.botClass;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.MessageParser;
import edu.java.bot.commands.Command;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import java.awt.*;


public class TgBot {
    final TelegramBot bot;
    final String token;

    public TgBot(String token) {
        this.token = token;
        this.bot = new TelegramBot(token);
    }

    public void start() {
        bot.setUpdatesListener(updates -> {
            for (var update : updates) {


                long chatId = update.message().chat().id();
                String userMessage = update.message().text();

                System.out.println("Сообщение пользователя получено" + userMessage);

                Command currentCommand = MessageParser.parse(userMessage);

                if (currentCommand != null) {
                    currentCommand.runHandler(update, this);
                } else {
                    System.out.println("currentCommand = null");
                }

            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public void sendMessage(Update update, String message) {
        long chatId = update.message().chat().id();
        bot.execute(new SendMessage(chatId, message));
    }

}
