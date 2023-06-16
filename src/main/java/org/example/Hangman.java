package org.example;

public class Hangman extends Game {

    public static void main(String[] args) {
        Hangman Hangman = new Hangman();

        System.out.println("Game");
        System.out.println("---------------");

        boolean playAgain = true;
        while (playAgain) {
            Hangman.setupGame();
            Hangman.playGame();
            playAgain = Hangman.askToPlayAgain();

            DropboxExample dropboxExample = new DropboxExample();
            try {
                String generatedString = dropboxExample.generateString();
                dropboxExample.uploadDropbox("data/overallStatistics.txt", "/dir/data" + generatedString + ".txt");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println("Thank you for playing !");
        System.out.println("""
                 ________  __                            __                                            __\s
                |        \\|  \\                          |  \\                                          |  \\
                 \\$$$$$$$$| $$____    ______   _______  | $$   __        __    __   ______   __    __ | $$
                   | $$   | $$    \\  |      \\ |       \\ | $$  /  \\      |  \\  |  \\ /      \\ |  \\  |  \\| $$
                   | $$   | $$$$$$$\\  \\$$$$$$\\| $$$$$$$\\| $$_/  $$      | $$  | $$|  $$$$$$\\| $$  | $$| $$
                   | $$   | $$  | $$ /      $$| $$  | $$| $$   $$       | $$  | $$| $$  | $$| $$  | $$ \\$$
                   | $$   | $$  | $$|  $$$$$$$| $$  | $$| $$$$$$\\       | $$__/ $$| $$__/ $$| $$__/ $$ __\s
                   | $$   | $$  | $$ \\$$    $$| $$  | $$| $$  \\$$\\       \\$$    $$ \\$$    $$ \\$$    $$|  \\
                    \\$$    \\$$   \\$$  \\$$$$$$$ \\$$   \\$$ \\$$   \\$$       _\\$$$$$$$  \\$$$$$$   \\$$$$$$  \\$$
                                                                        |  \\__| $$                       \s
                                                                         \\$$    $$                       \s
                                                                          \\$$$$$$                        \s""");
        System.out.println("---------------");
    }
}