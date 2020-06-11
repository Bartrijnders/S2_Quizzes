package com.rijnders.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class StandardUserTest {

    private final UUID id = UUID.randomUUID();
    private final String username = "username";
    private final String email = "username@email.com";
    private final String password = "password";
    private StandardUser toTest;

    @BeforeEach
    void setUp() {
        toTest = new StandardUser(id, username, email, password);
    }

    @Test
    void itShouldGetUserName() {
        //Given
        //When
        String actual = toTest.getUserName();
        //Then
        assertThat(actual).isEqualTo(username);
    }
}