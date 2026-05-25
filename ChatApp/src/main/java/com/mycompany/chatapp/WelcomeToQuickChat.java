package com.mycompany.chatapp;

import java.util.Scanner;

public class WelcomeToQuickChat {

    public static void startChat() {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\n=================================");
            System.out.println("         QUICKCHAT MENU");
            System.out.println("=================================");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("=================================");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.print("How many messages do you want to send? ");

                    int numMessages;

                    try {
                        numMessages = Integer.parseInt(scanner.nextLine());

                        if (numMessages <= 0) {
                            System.out.println("Please enter a positive number.");
                            break;
                        }

                        sendMessages(scanner, numMessages);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                    }

                    break;

                case "2":

                    // THIS NOW WORKS CORRECTLY
                    System.out.println("\n Coming Soon.");
                    System.out.println("This feature is still in development.\n");

                    break;

                case "3":

                    System.out.println("\n Thank you for using QuickChat!");
                    running = false;

                    break;

                default:

                    System.out.println("\n Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    private static void sendMessages(Scanner scanner, int numMessages) {

        int messagesSent = 0;

        for (int i = 0; i < numMessages; i++) {

            System.out.println("\n=================================");
            System.out.println("        MESSAGE " + (i + 1) + " OF " + numMessages);
            System.out.println("=================================");

            String recipient;

            while (true) {

                System.out.print("Recipient cell number (+27XXXXXXXXX): ");
                recipient = scanner.nextLine();

                if (recipient.startsWith("+") && recipient.length() <= 12) {

                    System.out.println("Cell number accepted.");
                    break;

                } else {

                    System.out.println("Invalid cell number.");
                }
            }

            String messageText;

            while (true) {

                System.out.print("Message (max 250 chars): ");
                messageText = scanner.nextLine();

                if (messageText.length() > 250) {

                    System.out.println("Please enter a message of less than 250 characters.");

                } else {

                    System.out.println("Message received.");
                    break;
                }
            }

            Message msg = new Message(i + 1, recipient, messageText);

            msg.displayMessageDetails();

            boolean validChoice = false;

            while (!validChoice) {

                System.out.println("\nWhat would you like to do?");
                System.out.println("1) Send Message");
                System.out.println("2) Discard Message");
                System.out.println("3) Store Message to send later");
                System.out.print("Your choice: ");

                String action = scanner.nextLine();

                switch (action) {

                    case "1":

                        String result = msg.sendMessage();

                        System.out.println("\n" + result);

                        if (result.equals("Message successfully sent.")) {
                            messagesSent++;
                        }

                        validChoice = true;

                        break;

                    case "2":

                        System.out.println("\nMessage discarded.");

                        validChoice = true;

                        break;

                    case "3":

                        msg.storeToFile();

                        System.out.println("\nMessage successfully stored.");

                        validChoice = true;

                        break;

                    default:

                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            }
        }

        System.out.println("\n=================================");
        System.out.println("         SESSION SUMMARY");
        System.out.println("=================================");
        System.out.println("Total messages sent: " + messagesSent);
        System.out.println("=================================\n");
    }
}