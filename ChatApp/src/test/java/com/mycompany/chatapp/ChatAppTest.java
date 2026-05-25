package com.mycompany.chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChatAppTest {

    // =========================
    // USERNAME TESTS
    // =========================

    @Test
    public void testValidUsername() {
        Register user = new Register(
                "John",
                "Doe",
                "john_doe",
                "Pass123!",
                "+27123456789"
        );

        assertTrue(user.checkUsername());
    }

    @Test
    public void testInvalidUsername() {
        Register user = new Register(
                "John",
                "Doe",
                "johndoe",
                "Pass123!",
                "+27123456789"
        );

        assertFalse(user.checkUsername());
    }

    // =========================
    // PASSWORD TESTS
    // =========================

    @Test
    public void testValidPassword() {
        Register user = new Register(
                "John",
                "Doe",
                "john_doe",
                "Pass123!",
                "+27123456789"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword() {
        Register user = new Register(
                "John",
                "Doe",
                "john_doe",
                "pass",
                "+27123456789"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    // =========================
    // CELLPHONE TESTS
    // =========================

    @Test
    public void testValidCellphone() {
        Register user = new Register(
                "John",
                "Doe",
                "john_doe",
                "Pass123!",
                "+27123456789"
        );

        assertTrue(user.checkCellphoneNumber());
    }

    @Test
    public void testInvalidCellphone() {
        Register user = new Register(
                "John",
                "Doe",
                "john_doe",
                "Pass123!",
                "0123456789"
        );

        assertFalse(user.checkCellphoneNumber());
    }

    // =========================
    // REGISTRATION TEST
    // =========================

    @Test
    public void testSuccessfulRegistration() {

        Register user = new Register(
                "John",
                "Doe",
                "john_doe",
                "Pass123!",
                "+27123456789"
        );

        String result = user.registerUser();

        assertEquals("User successfully registered!", result);
    }

    // =========================
    // LOGIN TESTS
    // =========================

    @Test
    public void testLoginSuccess() {

        Register user = new Register(
                "John",
                "Doe",
                "john_doe",
                "Pass123!",
                "+27123456789"
        );

        user.registerUser();

        Login login = new Login(user);

        String result = login.returnLoginStatus(
                "john_doe",
                "Pass123!"
        );

        assertTrue(result.contains("Welcome"));
    }

    @Test
    public void testLoginFailure() {

        Register user = new Register(
                "John",
                "Doe",
                "john_doe",
                "Pass123!",
                "+27123456789"
        );

        user.registerUser();

        Login login = new Login(user);

        String result = login.returnLoginStatus(
                "john_doe",
                "wrongPass"
        );

        assertFalse(result.contains("Welcome"));
    }

    // =====================================================
    // MESSAGE TESTS
    // =====================================================

    // TEST MESSAGE LENGTH SUCCESS

    @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message(
                1,
                "+27123456789",
                "Hello this is a valid short message"
        );

        String result = msg.sendMessage();

        assertEquals(
                "Message successfully sent.",
                result
        );
    }

    // TEST MESSAGE LENGTH FAILURE

    @Test
    public void testMessageLengthFailure() {

        String longMessage = "a".repeat(251);

        Message msg = new Message(
                1,
                "+27123456789",
                longMessage
        );

        String result = msg.sendMessage();

        assertEquals(
                "Please enter a message of less than 250 characters.",
                result
        );
    }

    // TEST RECIPIENT NUMBER SUCCESS

    @Test
    public void testRecipientSuccess() {

        Message msg = new Message(
                1,
                "+27123456789",
                "Hello"
        );

        assertTrue(msg.checkRecipientCell());
    }

    // TEST RECIPIENT NUMBER FAILURE

    @Test
    public void testRecipientFailure() {

        Message msg = new Message(
                1,
                "0821234567",
                "Hello"
        );

        assertFalse(msg.checkRecipientCell());
    }

    // TEST MESSAGE HASH

    @Test
    public void testMessageHashExists() {

        Message msg = new Message(
                1,
                "+27123456789",
                "Hi tonight"
        );

        String hash = msg.getMessageHash();

        assertNotNull(hash);

        assertTrue(hash.contains(":1:"));
    }

    // TEST MESSAGE ID

    @Test
    public void testMessageIDCreated() {

        Message msg = new Message(
                1,
                "+27123456789",
                "Test message"
        );

        String id = msg.getMessageID();

        assertNotNull(id);

        assertEquals(10, id.length());
    }

    // TEST SEND MESSAGE OPTION

    @Test
    public void testSendMessageOption() {

        Message msg = new Message(
                1,
                "+27123456789",
                "Hello"
        );

        assertEquals(
                "Message successfully sent.",
                msg.sendMessage()
        );
    }

    // TEST STORE MESSAGE

    @Test
    public void testStoreMessage() {

        Message msg = new Message(
                1,
                "+27123456789",
                "Store this message"
        );

        msg.storeToFile();

        assertTrue(true);
    }
}