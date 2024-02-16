package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;

public class Untrack implements Command{
    @Override
    public void runHandler(Update update, TgBot bot) {
        System.out.println("Тут будет ручка для Untrack");
    }
}
