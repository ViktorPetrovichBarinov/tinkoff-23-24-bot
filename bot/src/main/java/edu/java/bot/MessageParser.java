package edu.java.bot;

import edu.java.bot.commands.Command;

import java.lang.reflect.InvocationTargetException;

public class MessageParser {
    private static final String PATH_TO_COMMANDS_CLASSES = "edu.java.bot.commands.";
    public static Command parse(String message) {
        System.out.println("Запустился парсер");
        String[] partsOfString = message.split("\\s+");
        for (String command : partsOfString) {
            if (command.startsWith("/") && command.length() >= 3) {
                String nameOfClass = PATH_TO_COMMANDS_CLASSES + Character.toUpperCase(command.charAt(1)) + command.substring(2).toLowerCase();
                System.out.println("Название класс " + nameOfClass);

                try {
                    Class<?> currentClass = Class.forName(nameOfClass);
                    Class<?> currentInterface = Command.class;
                    if(currentInterface.isAssignableFrom(currentClass)) {
                        System.out.println("Ура");
                        return (Command) currentClass.getDeclaredConstructor().newInstance();
                    } else {
                        throw new ClassNotFoundException();
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Анлак");
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            } else {
                continue;
            }
        }
        return null;
    }
}
