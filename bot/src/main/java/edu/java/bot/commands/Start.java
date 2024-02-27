package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;
import edu.java.bot.botClass.User;
import java.util.ArrayList;

public class Start implements Command {
    @Override
    public void runHandler(Update update, TgBot bot) {
        long id = update.message().from().id();
        if (bot.users.containsKey(id)) {
            bot.sendMessage(update, "You are already registered.");
        } else {
            User user = new User(true, new ArrayList<>());
            bot.users.put(id, user);
            bot.sendMessage(update, "You has registered");
        }
    }
}
