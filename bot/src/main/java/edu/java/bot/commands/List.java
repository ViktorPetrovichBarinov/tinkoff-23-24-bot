package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;

public class List implements Command {
    @Override
    public void runHandler(Update update, TgBot bot) {
        StringBuilder sb = new StringBuilder();
        Long id = update.message().from().id();
        if (bot.users.containsKey(id)) {
            java.util.List<String> links = bot.users.get(id).links();
            for (int i = 0; i < links.size(); i++) {
                sb.append(Integer.valueOf(i + 1).toString()).append(". ").append(links.get(i)).append("\n");
            }

            if (sb.isEmpty()) {
                bot.sendMessage(update, "You haven't any link to tracking");
            }
        }
        bot.sendMessage(update, sb.toString());
    }
}
