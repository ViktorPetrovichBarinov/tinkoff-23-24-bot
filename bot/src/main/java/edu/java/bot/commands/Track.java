package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;
import edu.java.bot.botClass.User;
import java.util.HashMap;


public class Track implements Command {
    @Override
    public void runHandler(Update update, TgBot bot) {
        String message = update.message().text();
        String link = getLink(message);
        if (link.isEmpty()) {
            bot.sendMessage(update, "error: /track {YOUR_LINK}");
        } else {
            addLink(update, link, bot);
        }

    }

    private void addLink(Update update, String link, TgBot bot) {
        Long id = update.message().chat().id();
        HashMap<Long, User> map = bot.users;
        if (map.containsKey(id)) {
            for (String tmpLink : map.get(id).links()) {
                if (link.equals(tmpLink)) {
                    bot.sendMessage(update, "you are already track this link");
                    return;
                }
            }
            map.get(id).links().add(link);
            bot.sendMessage(update, "link was added");
        } else {
            bot.sendMessage(update, "You haven't registered. write - \"/start\"");
        }

    }

    private static final String PREFIX = "/track";

    private String getLink(String message) {
        String[] parts = message.split("\\s+");
        if (parts.length >= 2 && parts[0].equals(PREFIX) && parts[1].startsWith("https://")) {
            return parts[1];
        } else {
            return "";
        }
    }
}
