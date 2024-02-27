package edu.java.bot.botClass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    @DisplayName("Setters")
    void test1() {
        List list = new ArrayList();
        list.add("link");
        User user = new User(true, list);

        assertThat(user.isRegistered()).isTrue();
        assertThat(user.links().get(0)).isEqualTo("link");
    }
}
