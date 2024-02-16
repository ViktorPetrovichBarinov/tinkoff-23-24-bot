package edu.java.bot;

import edu.java.bot.commands.Command;
import java.lang.reflect.InvocationTargetException;

public class MessageParser {
    private MessageParser() {

    }

    private static final String PATH_TO_COMMANDS_CLASSES = "edu.java.bot.commands.";
    private static final int MIN_LENGTH_OF_COMMAND = 3;

    public static Command parse(String message) {
        String[] partsOfString = message.split("\\s+");
        for (String command : partsOfString) {
            if (command.startsWith("/") && command.length() >= MIN_LENGTH_OF_COMMAND) {
                String nameOfClass = PATH_TO_COMMANDS_CLASSES + Character.toUpperCase(command.charAt(1))
                        + command.substring(2).toLowerCase();

                try {
                    Class<?> currentClass = Class.forName(nameOfClass);
                    Class<?> currentInterface = Command.class;
                    if (currentInterface.isAssignableFrom(currentClass)) {
                        return (Command) currentClass.getDeclaredConstructor().newInstance();
                    }
                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException
                         | IllegalAccessException | NoSuchMethodException ignored) {
                }
            }
        }
        return null;
    }
}
