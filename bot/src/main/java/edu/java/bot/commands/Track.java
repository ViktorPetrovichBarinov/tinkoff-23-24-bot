package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;

public class Track implements Command {
    @Override
    public void runHandler(Update update, TgBot bot) {
        bot.sendMessage(update, "Тут будет ручка для Track");
    }
}
