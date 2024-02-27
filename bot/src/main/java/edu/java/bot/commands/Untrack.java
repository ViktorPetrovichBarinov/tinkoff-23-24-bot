package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;
import edu.java.bot.botClass.User;
import java.util.HashMap;
import java.util.List;

public class Untrack implements Command {
    @Override
    public void runHandler(Update update, TgBot bot) {
        int linkNumber = getNumberOfLink(update);
        if (linkNumber == -1) {
            bot.sendMessage(update, "/untrack {LINK_NUMBER}");
        } else {
            deleteLink(update, linkNumber, bot);
        }
    }

    private void deleteLink(Update update, int linkNumber, TgBot bot) {
        Long id = update.message().chat().id();
        HashMap<Long, User> map = bot.users;
        if (map.containsKey(id)) {
            List<String> links = map.get(id).links();
            if (links.size() >= linkNumber - 1) {
                links.remove(linkNumber - 1);
            } else {
                bot.sendMessage(update, "You haven't link with such number. list of links - \"/list\"");
                return;
            }

            bot.sendMessage(update, "link was deleted");
        } else {
            bot.sendMessage(update, "You haven't registered. write - \"/start\"");
        }
    }

    private static final String PREFIX = "/untrack";

    private int getNumberOfLink(Update update) {
        String message = update.message().text();
        String[] parts = message.split("\\s+");
        if (parts.length >= 2 && parts[0].equals(PREFIX)) {
            try {
                return Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                return -1;
            }

        } else {
            return -1;
        }
    }
}
