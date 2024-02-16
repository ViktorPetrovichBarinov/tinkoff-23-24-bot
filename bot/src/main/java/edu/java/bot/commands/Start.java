package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;

public class Start implements Command{
    @Override
    public void runHandler(Update update, TgBot bot) {
        bot.sendMessage(update, "You has registered");
    }
}
