package edu.java.bot.botClass;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import edu.java.bot.MessageParser;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TgBot {
    private final TelegramBot bot;
    public final HashMap<Long, User> users = new HashMap<>();

    public TgBot(String token) {
        this.bot = new TelegramBot(token);
        menuSet();
    }

    private void menuSet() {
        List<BotCommand> commandList = new ArrayList();
        commandList.add(new BotCommand("start", "start using."));
        commandList.add(new BotCommand("help", "call a hint."));
        commandList.add(new BotCommand("track", "start tracking links."));
        commandList.add(new BotCommand("untrack", "stop tracking links."));
        commandList.add(new BotCommand("list", "display a list of tracking links."));
        SetMyCommands setMyCommands = new SetMyCommands(commandList.toArray(new BotCommand[0]));
        this.bot.execute(setMyCommands);
    }

    public void start() {
        bot.setUpdatesListener(updates -> {
            for (var update : updates) {
                String userMessage = update.message().text();

                Command currentCommand = MessageParser.parse(userMessage);

                if (currentCommand != null) {
                    currentCommand.runHandler(update, this);
                } else {
                    Help help = new Help();
                    help.runHandler(update, this);
                }

            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public void sendMessage(Update update, String message) {
        long chatId = update.message().chat().id();
        bot.execute(new SendMessage(chatId, message));
    }
}
