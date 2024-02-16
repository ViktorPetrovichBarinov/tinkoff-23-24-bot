package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.botClass.TgBot;

public class Help implements Command{
    @Override
    public void runHandler(Update update, TgBot bot) {
        String message = """
                /start - начать использование
                /help - вызвать подсказку
                /track - начать отслеживание ссылки
                /untrack - прекратить отслеживание ссылки
                /list - вывести список отслеживаемых ссылок
                """;
        bot.sendMessage(update, message);
    }
}
