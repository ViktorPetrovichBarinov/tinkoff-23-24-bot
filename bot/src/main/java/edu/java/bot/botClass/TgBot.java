package edu.java.bot.botClass;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.MessageParser;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import java.util.HashMap;

public class TgBot {
    private final TelegramBot bot;
    public final HashMap<Long, User> users = new HashMap<>();

    public TgBot(String token) {
        this.bot = new TelegramBot(token);
    }

    public void start() {
        bot.setUpdatesListener(updates -> {
            for (var update : updates) {
                String userMessage = update.message().text();

                Command currentCommand = MessageParser.parse(userMessage);

                if (currentCommand != null) {
                    currentCommand.runHandler(update, this);
                } else {
                    Help help = new Help();
                    help.runHandler(update, this);
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
