package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;

public class Help implements Command {
    @Override
    public void runHandler(Update update, TgBot bot) {
        String message = """
                /start - start using.
                /help - call a hint
                /track - start tracking links
                /untrack - stop tracking links
                /list - display a list of tracking links
                """;
        bot.sendMessage(update, message);
    }
}
