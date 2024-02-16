package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;

public class List implements Command {
    @Override
    public void runHandler(Update update, TgBot bot) {
        StringBuilder sb = new StringBuilder();
        Long id = update.message().from().id();
        if (bot.users.containsKey(id)) {
            for (String link : bot.users.get(id).links()) {
                sb.append(link + "\n");
            }
            if (sb.isEmpty()) {
                bot.sendMessage(update, "You haven't any link to tracking");
            }
        }
        bot.sendMessage(update, sb.toString());
    }
}
