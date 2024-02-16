package edu.java.bot;

import edu.java.bot.commands.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageParserTest {

    @Test
    @DisplayName("Тест команды /start")
    void test1() {
        String given = "/start";

        Command command = new Start();

        // Проверка, что command является экземпляром (instanceof) интерфейса Command
        assertThat(command).isInstanceOf(Command.class);

        // Проверка, что MessageParser.parse(given) также возвращает объект, являющийся экземпляром интерфейса Command
        assertThat(MessageParser.parse(given)).isInstanceOf(Command.class);

        // Можно также проверить, что объекты имеют одинаковый класс
        assertThat(command).isInstanceOf(MessageParser.parse(given).getClass());
    }


    @Test
    @DisplayName("Тест команды /help")
    void test2() {
        String given = "/help";

        Command command = new Help();

        assertThat(command).isInstanceOf(Command.class);

        assertThat(MessageParser.parse(given)).isInstanceOf(Command.class);

        assertThat(command).isInstanceOf(MessageParser.parse(given).getClass());
    }

    @Test
    @DisplayName("Тест команды /list")
    void test3() {
        String given = "/list";

        Command command = new List();

        assertThat(command).isInstanceOf(Command.class);

        assertThat(MessageParser.parse(given)).isInstanceOf(Command.class);

        assertThat(command).isInstanceOf(MessageParser.parse(given).getClass());
    }

    @Test
    @DisplayName("Тест команды /track")
    void test4() {
        String given = "/track";

        Command command = new Track();

        assertThat(command).isInstanceOf(Command.class);

        assertThat(MessageParser.parse(given)).isInstanceOf(Command.class);

        assertThat(command).isInstanceOf(MessageParser.parse(given).getClass());
    }

    @Test
    @DisplayName("Тест команды /untrack")
    void test5() {
        String given = "/untrack";

        Command command = new Untrack();

        assertThat(command).isInstanceOf(Command.class);

        assertThat(MessageParser.parse(given)).isInstanceOf(Command.class);

        assertThat(command).isInstanceOf(MessageParser.parse(given).getClass());
    }

    @Test
    @DisplayName("Тест ошибочного сообщения")
    void test6() {
        String given = "Hello, i'm Russel. How are you?";

        assertThat(MessageParser.parse(given)).isNull();
    }

    @Test
    @DisplayName("Тест сообщение содержащее в себе две команды")
    void test7() {
        String given = "Hello, i'm Russel. /start  /list How are you?";

        Command command = new Start();

        assertThat(command).isInstanceOf(Command.class);

        assertThat(MessageParser.parse(given)).isInstanceOf(Command.class);

        assertThat(command).isInstanceOf(MessageParser.parse(given).getClass());
    }

    @Test
    @DisplayName("Тест сообщение содержащее в себе несуществующие команды")
    void test8() {
        String given = "Hello, i'm Russel. /aaaa  How are you?";

        Command command = new Start();

        assertThat(MessageParser.parse(given)).isNull();
    }
}
