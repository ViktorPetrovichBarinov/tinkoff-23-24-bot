package edu.java.bot.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;

public interface Command {
    void runHandler(Update update, TgBot bot);
}