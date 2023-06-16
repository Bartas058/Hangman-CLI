package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game extends Stats {

    private static final List<String> playersNicknames = new ArrayList<>(List.of());

    private List<Character> usedLetters;
    private int currentWins = 0;
    private int currentLosses = 0;
    private int gamesTotal = 0;
    private int wins = 0;
    private int losses = 0;
    private int overallGames = 0;
    private int incorrectAnswers = 0;
    private int correctAnswers = 0;
    private String currentUsername;

    private static final List<String> customWords = new ArrayList<>(List.of());


    private static final Random random = new Random();
    private static final List<String> easyWordsEnglish = new ArrayList<>(Arrays.asList("cat", "dog", "sun", "hat", "book", "ball", "tree", "fish", "bird", "rain"));        // Od 3 do 4 liter
    private static final List<String> mediumWordsEnglish = new ArrayList<>(Arrays.asList("apple", "table", "guitar", "window", "coffee", "soccer", "letter", "piano", "mirror", "candle"));     // Od 5 do 6 liter
    private static final List<String> hardWordsEnglish = new ArrayList<>(Arrays.asList("parliament", "xenophobia", "onomatopoeia", "pneumonia", "chrysanthemum", "perseverance", "resilience", "imagination", "serendipity", "accomplish"));     // Od 9 do 13 liter

    private static final List<String> easyWordsSpanish = new ArrayList<>(Arrays.asList("sol", "mar", "pan", "luz", "voz", "rey", "flor", "oro", "lago", "rey"));        // Od 3 do 4 liter
    private static final List<String> mediumWordsSpanish = new ArrayList<>(Arrays.asList("agua", "café", "llave", "manos", "mesa", "perro", "pared", "ruido", "silla", "techo"));       // Od 5 do 6 liter
    private static final List<String> hardWordsSpanish = new ArrayList<>(Arrays.asList("estudiante", "biblioteca", "universidad", "aventurero", "campeonato", "restaurant", "desayunador", "aeropuerto", "extraterrestre", "investigador"));    // Od 9 do 13 liter

    private static final Map<String, String> learningMode = new HashMap<>();
    static {
        learningMode.put("chocolate", "a food preparation made from roasted and ground cacao seeds, typically sweetened");
        learningMode.put("computer", "an electronic device for processing and storing information");
        learningMode.put("music", "sounds that are arranged in a pleasing way");
        learningMode.put("friend", "a person you like and enjoy being with");
        learningMode.put("friendship", "the emotions or conduct of friends; the state of being friends");
        learningMode.put("beach", "a sandy or pebbly area by the sea or a lake");
        learningMode.put("phone", "a telecommunications device that allows two or more users to conduct a conversation when they are too far apart to be heard directly");
        learningMode.put("rainbow", "a multicolored arc in the sky");
        learningMode.put("journey", "a trip or traveling from one place to another");
        learningMode.put("sunflower", "a tall plant with large yellow flowers");
    }
    private int remainingTries;
    Scanner scanner = new Scanner(System.in);
    private String secretWord;
    private StringBuilder guessedWord;

    public void choosePathEnglish() {
        boolean exitMenu = false;

        while(!exitMenu) {
            System.out.println("---------------");
            System.out.println("Choose your path:");
            System.out.println("1. Based on word length");
            System.out.println("2. Based on the number of trials");
            System.out.println("3. Learning mode - recommended for children");
            System.out.print(">>> ");

            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: invalid choice. The choice cannot be empty");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        chooseDifficultyWordLengthEnglish();
                        exitMenu = true;
                    }
                    case 2 -> {
                        chooseDifficultyNumberOfTrialsEnglish();
                        exitMenu = true;
                    }
                    case 3 -> {
                        learningMode();
                        exitMenu = true;
                    }
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid choice");
            }
        }
    }

    public void choosePathSpanish() {
        boolean exitMenu = false;

        while(!exitMenu) {
            System.out.println("---------------");
            System.out.println("Choose your path:");
            System.out.println("1. Based on word length");
            System.out.println("2. Based on the number of trials");
            System.out.print(">>> ");

            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: invalid choice. The choice cannot be empty");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        chooseDifficultyWordLengthSpanish();
                        exitMenu = true;
                    }
                    case 2 -> {
                        chooseDifficultyNumberOfTrialsSpanish();
                        exitMenu = true;
                    }
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid choice");
            }
        }
    }

    public void chooseLanguage() {
        boolean exitMenu = false;

        while(!exitMenu) {
            System.out.println("---------------");
            System.out.println("Select the language:");
            System.out.println("1. English");
            System.out.println("2. Spanish");
            System.out.print(">>> ");

            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error invalid choice. The choice cannot be empty");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        choosePathEnglish();
                        exitMenu = true;
                    }
                    case 2 -> {
                        choosePathSpanish();
                        exitMenu = true;
                    }
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid choice");
            }
        }
    }

    public void chooseDifficultyWordLengthEnglish() {
        boolean exitMenu = false;

        while(!exitMenu) {
            System.out.println("---------------");
            System.out.println("Enter the level of difficulty:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print(">>> ");

            remainingTries = 7;

            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: invalid choice. The choice cannot be empty");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        if (!easyWordsEnglish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(easyWordsEnglish).length()));
                            secretWord = getRandomWordFromList(easyWordsEnglish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyWordLengthEnglish();
                        }
                        exitMenu = true;
                    }
                    case 2 -> {
                        if (!mediumWordsEnglish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(mediumWordsEnglish).length()));
                            secretWord = getRandomWordFromList(mediumWordsEnglish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyWordLengthEnglish();
                        }
                        exitMenu = true;
                    }
                    case 3 -> {
                        if (!hardWordsEnglish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(hardWordsEnglish).length()));
                            secretWord = getRandomWordFromList(hardWordsEnglish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                        }
                        exitMenu = true;
                    }
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid choice");
            }
        }
    }

    public void chooseDifficultyWordLengthSpanish() {
        boolean exitMenu = false;

        while(!exitMenu) {
            System.out.println("---------------");
            System.out.println("Enter the level of difficulty:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print(">>> ");

            remainingTries = 7;

            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: invalid choice. The choice cannot be empty");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        if (!easyWordsSpanish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(easyWordsSpanish).length()));
                            secretWord = getRandomWordFromList(easyWordsSpanish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyWordLengthEnglish();
                        }
                        exitMenu = true;
                    }
                    case 2 -> {
                        if (!mediumWordsSpanish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(mediumWordsSpanish).length()));
                            secretWord = getRandomWordFromList(mediumWordsSpanish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyWordLengthEnglish();
                        }
                        exitMenu = true;
                    }
                    case 3 -> {
                        if (!hardWordsSpanish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(hardWordsSpanish).length()));
                            secretWord = getRandomWordFromList(hardWordsSpanish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                        }
                        exitMenu = true;
                    }
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid choice");
            }
        }
    }

    public void chooseDifficultyNumberOfTrialsEnglish() {
        boolean exitMenu = false;

        while (!exitMenu) {
            System.out.println("---------------");
            System.out.println("Enter the level of difficulty:");
            System.out.println("1. Easy - 10 trials");
            System.out.println("2. Medium - 7 trials");
            System.out.println("3. Hard - 5 trials");
            System.out.print(">>> ");

            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: invalid input. The choice cannot be empty");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        remainingTries = 10;
                        if (!easyWordsEnglish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(easyWordsEnglish).length()));
                            secretWord = getRandomWordFromList(easyWordsEnglish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyWordLengthEnglish();
                        }
                        exitMenu = true;
                    }
                    case 2 -> {
                        remainingTries = 7;
                        if (!mediumWordsEnglish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(mediumWordsEnglish).length()));
                            secretWord = getRandomWordFromList(mediumWordsEnglish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyWordLengthEnglish();
                        }
                        exitMenu = true;
                    }
                    case 3 -> {
                        remainingTries = 5;
                        if (!hardWordsSpanish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(hardWordsEnglish).length()));
                            secretWord = getRandomWordFromList(hardWordsEnglish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyNumberOfTrialsEnglish();
                        }
                        exitMenu = true;
                    }
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid choice");
            }
        }
    }

    public void chooseDifficultyNumberOfTrialsSpanish() {
        boolean exitMenu = false;

        while (!exitMenu) {
            System.out.println("---------------");
            System.out.println("Enter the level of difficulty:");
            System.out.println("1. Easy - 10 trials");
            System.out.println("2. Medium - 7 trials");
            System.out.println("3. Hard - 5 trials");
            System.out.print(">>> ");

            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: invalid input. The choice cannot be empty");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        remainingTries = 10;
                        if (!easyWordsSpanish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(easyWordsSpanish).length()));
                            secretWord = getRandomWordFromList(easyWordsSpanish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyWordLengthSpanish();
                        }
                        exitMenu = true;
                    }
                    case 2 -> {
                        remainingTries = 7;
                        if (!mediumWordsSpanish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(mediumWordsSpanish).length()));
                            secretWord = getRandomWordFromList(mediumWordsSpanish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyWordLengthSpanish();
                        }
                        exitMenu = true;
                    }
                    case 3 -> {
                        remainingTries = 5;
                        if (!hardWordsSpanish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(hardWordsSpanish).length()));
                            secretWord = getRandomWordFromList(hardWordsSpanish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            chooseDifficultyNumberOfTrialsEnglish();
                        }
                        exitMenu = true;
                    }
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid choice");
            }
        }
    }

    public void learningMode() {
        boolean exitMenu = false;

        while (!exitMenu) {
            System.out.println("---------------");
            System.out.println("Enter the level of difficulty:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print(">>> ");

            String input = scanner.nextLine();
            remainingTries = 7;

            if (input.isEmpty()) {
                System.out.println("Error: invalid. The choice cannot be empty");
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        if (!easyWordsEnglish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromMap(learningMode).length()));
                            secretWord = getRandomWordFromMap(learningMode);
                            System.out.println("Definition: " + learningMode.get(secretWord));
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            learningMode();
                        }
                        exitMenu = true;
                    }
                    case 2 -> {
                        if (!mediumWordsEnglish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(mediumWordsEnglish).length()));
                            secretWord = getRandomWordFromList(mediumWordsEnglish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            learningMode();
                        }
                        exitMenu = true;
                    }
                    case 3 -> {
                        if (!hardWordsEnglish.isEmpty()) {
                            guessedWord = new StringBuilder("_".repeat(getRandomWordFromList(hardWordsEnglish).length()));
                            secretWord = getRandomWordFromList(hardWordsEnglish);
                        } else {
                            System.out.println("No words available for the selected difficulty level");
                            learningMode();
                        }
                        exitMenu = true;
                    }
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid choice");
            }
        }
    }


    public void setupGame() {

        setPlayer();
        addCustomWords();
        chooseLanguage();
        usedLetters = new ArrayList<>();
        guessedWord = new StringBuilder("_".repeat(secretWord.length()));

        System.out.println("---------------");
        System.out.println("Let's start the game!");
    }

    public void playGame() {
        List<Character> previousGuesses = new ArrayList<>();
        List<Character> correctGuesses = new ArrayList<>();
        List<Character> incorrectGuesses = new ArrayList<>();

        while (remainingTries > 0 && guessedWord.toString().contains("_")) {
            System.out.println("---------------");
            System.out.println("Secret word: " + guessedWord);
            System.out.println("Remaining tries: " + remainingTries);
            System.out.println("Used letters: " + usedLetters);

            if (learningMode.containsKey(secretWord)) {
                System.out.println("Definition: " + learningMode.get(secretWord));
            }

            char letter = inputLetter(previousGuesses);

            if (letter == '8') {
                if (!previousGuesses.isEmpty()) {
                    char lastGuess = previousGuesses.remove(previousGuesses.size() - 1);
                    usedLetters.remove((Character) lastGuess);
                    if (correctGuesses.contains(lastGuess)) {
                        correctGuesses.remove((Character) lastGuess);
                        refreshGuessedWord(correctGuesses);
                        correctAnswers += 1;
                        updateOverallStats(wins, losses, overallGames, correctAnswers, incorrectAnswers);
                    } else if (incorrectGuesses.contains(lastGuess)) {
                        incorrectGuesses.remove((Character) lastGuess);
                        remainingTries++;
                        incorrectAnswers -= 1;
                        updateOverallStats(wins, losses, overallGames, correctAnswers, incorrectAnswers);
                    }
                    System.out.println("---------------");
                    System.out.println("Undo successful!");
                } else {
                    System.out.println("---------------");
                    System.out.println("No moves to undo");
                }
                continue;
            }

            if (usedLetters.contains(letter)) {
                System.out.println("---------------");
                System.out.println("You've already used this letter. Try again");
                continue;
            }
            usedLetters.add(letter);
            previousGuesses.add(letter);

            if (secretWord.contains(String.valueOf(letter))) {
                correctGuesses.add(letter);
                updateGuessedWord(letter);
                System.out.println("---------------");
                System.out.println("Good guess!");
                correctAnswers += 1;
                updateOverallStats(wins, losses, overallGames, correctAnswers, incorrectAnswers);
            } else {
                displayHangmanWordLength();
                incorrectGuesses.add(letter);
                remainingTries--;
                incorrectAnswers += 1;
                updateOverallStats(wins, losses, overallGames, correctAnswers, incorrectAnswers);
                System.out.println("---------------");
                System.out.println("Wrong guess!");
            }
        }

        if (remainingTries > 0) {
            System.out.println("---------------");
            System.out.println("Congratulations! You won!");
            System.out.println("The word was: " + secretWord);
            wins += 1;
            currentWins += 1;
            gamesTotal += 1;
            overallGames += 1;
            updateOverallStats(wins, losses, overallGames, correctAnswers, incorrectAnswers);
            updatePlayerStats(currentUsername, currentWins, currentLosses, gamesTotal);
        } else {
            System.out.println("---------------");
            System.out.println("Game over! You lost.");
            System.out.println("The word was: " + secretWord);
            losses += 1;
            currentLosses += 1;
            gamesTotal += 1;
            overallGames += 1;
            updateOverallStats(wins, losses, overallGames, correctAnswers, incorrectAnswers);
            updatePlayerStats(currentUsername, currentWins, currentLosses, gamesTotal);
        }

        menuStats();
    }

    public void addCustomWords() {
        boolean addingWords = true;

        while (addingWords) {
            System.out.println("---------------");
            System.out.println("Do you want to add custom words? (Y/N):");
            System.out.print(">>> ");
            String input = scanner.nextLine().trim().toUpperCase();

            while (!input.equals("Y") && !input.equals("N")) {
                if (input.isEmpty()) {
                    System.out.println("---------------");
                    System.out.println("Error: invalid choice. The choice cannot be empty");
                } else {
                    System.out.println("---------------");
                    System.out.println("Error: invalid choice");
                }

                System.out.println("---------------");
                System.out.println("Do you want to add custom words? (Y/N):");
                System.out.print(">>> ");
                input = scanner.nextLine().trim().toUpperCase();
            }

            if (input.equals("Y")) {
                System.out.println("---------------");
                System.out.println("Select the language:");
                System.out.println("1. English");
                System.out.println("2. Spanish");
                System.out.println("3. Learning mode");
                System.out.println("4. Custom words");
                System.out.print(">>> ");
                input = scanner.nextLine().trim();

                while (!isValidLanguageChoice(input)) {
                    if (input.isEmpty()) {
                        System.out.println("---------------");
                        System.out.println("Error: invalid choice. The choice cannot be empty");
                    } else {
                        System.out.println("---------------");
                        System.out.println("Error: invalid choice");
                    }

                    System.out.println("---------------");
                    System.out.println("Select the language:");
                    System.out.println("1. English");
                    System.out.println("2. Spanish");
                    System.out.println("3. Learning mode");
                    System.out.println("4. Custom words");
                    System.out.print(">>> ");
                    input = scanner.nextLine().trim();
                }

                System.out.println("---------------");
                System.out.println("Enter a custom word: ");
                System.out.print(">>> ");
                String customWord = scanner.nextLine().trim();

                if (customWord.isEmpty()) {
                    System.out.println("---------------");
                    System.out.println("Error: invalid word. The word cannot be empty");
                    continue;
                }

                System.out.println("---------------");
                System.out.println("Word " + customWord + " has been added!");

                switch (input) {
                    case "1" -> {
                        addWordToDatabase(customWord, easyWordsEnglish);
                        displayCustomWordsDatabase(easyWordsEnglish);
                    }
                    case "2" -> {
                        addWordToDatabase(customWord, easyWordsSpanish);
                        displayCustomWordsDatabase(easyWordsSpanish);
                    }
                    case "3" -> {
                        System.out.println("Enter the definition for " + customWord + ": ");
                        System.out.print(">>> ");
                        String customDefinition = scanner.nextLine().trim();
                        if (customDefinition.isEmpty()) {
                            System.out.println("---------------");
                            System.out.println("Error: invalid definition. The definition cannot be empty");
                            continue;
                        }
                        System.out.println("---------------");
                        System.out.println("Word " + customWord + " with definition \"" + customDefinition + "\" has been added!");
                        learningMode.put(customWord, customDefinition);
                        displayLearningModeDatabase();
                    }
                    case "4" -> {
                        addWordToDatabase(customWord, customWords);
                        displayCustomWordsDatabase(customWords);
                    }
                }
            } else {
                addingWords = false;
            }
        }
    }

    private boolean isValidLanguageChoice(String input) {
        return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4");
    }

    private void addWordToDatabase(String word, List<String> database) {
        database.add(word);
    }

    private void displayCustomWordsDatabase(List<String> database) {
        System.out.println("Displaying the custom words database:");

        for (String word : database) {
            System.out.println(word);
        }
    }

    private void displayLearningModeDatabase() {
        System.out.println("Displaying the learning mode database:");

        for (Map.Entry<String, String> entry : learningMode.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void setPlayer() {
        System.out.println("Enter your username:");
        System.out.println("The username can have a maximum of 10 characters and must not contain spaces or special characters");
        System.out.print(">>> ");
        String nickname = scanner.nextLine();
        currentUsername = nickname;

        while (nickname.length() == 0 || nickname.length() > 10 || nickname.contains(" ") || !nickname.matches("[a-zA-Z0-9]+")) {
            if (nickname.length() == 0) {
                System.out.println("Error: username cannot be empty");
            } else if (nickname.length() > 10) {
                System.out.println("Error: username cannot exceed 10 characters");
            } else if (nickname.contains(" ")) {
                System.out.println("Error: username cannot contain spaces");
            } else {
                System.out.println("Error: username cannot containt special characters");
            }

            System.out.print(">>> ");
            nickname = scanner.nextLine();
        }

        if (playersNicknames.contains(nickname)) {
            System.out.println("---------------");
            System.out.println("Nice to see you back, " + nickname + "!");
            updateOverallStats(wins, losses, overallGames, correctAnswers, incorrectAnswers);
        } else {
            System.out.println("---------------");
            System.out.println("Hello " + nickname + "!");
            playersNicknames.add(nickname);
            currentWins = 0;
            currentLosses = 0;
            gamesTotal = 0;
            updatePlayerStats(nickname, currentWins, currentLosses, gamesTotal);
        }
    }

    public boolean askToPlayAgain() {
        System.out.println("---------------");
        System.out.println("Do you want to play again? (Y/N):");
        System.out.print(">>> ");
        String answer = scanner.nextLine().trim().toUpperCase();

        while (!answer.equals("Y") && !answer.equals("N")) {
            System.out.println("---------------");
            System.out.println("Error: invalid choice. Please enter 'Y' or 'N'");
            System.out.println("Do you want to play again? (Y/N):");
            System.out.print(">>> ");
            answer = scanner.nextLine().trim().toLowerCase();
        }

        System.out.println("---------------");
        return answer.equals("Y");
    }

    public String getRandomWordFromList(List<String> wordList) {
        return wordList.get(random.nextInt(wordList.size()));
    }

    public String getRandomWordFromMap(Map<String, String> wordMap) {
        List<String> wordList = new ArrayList<>(wordMap.keySet());
        return wordList.get(new Random().nextInt(wordList.size()));
    }

    public char inputLetter(List<Character> previousGuesses) {
        String input;

        if (previousGuesses.isEmpty()) {
            System.out.println("Enter a letter (or '8' to undo):");
        } else {
            System.out.println("Enter a letter (or '8' to undo)");
        }
        System.out.print(">>> ");
        input = scanner.nextLine().trim().toLowerCase();

        while (!input.matches("[a-z08]")) {
            System.out.println("---------------");
            System.out.println("Error: invalid input");
            System.out.print(">>> ");
            input = scanner.nextLine().trim().toLowerCase();
        }

        return input.charAt(0);
    }

    public void refreshGuessedWord(List<Character> correctGuesses) {
        guessedWord = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {
            char letter = secretWord.charAt(i);
            if (correctGuesses.contains(letter)) {
                guessedWord.append(letter);
            } else {
                guessedWord.append("_");
            }
        }
    }

    public void updateGuessedWord(char letter) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessedWord.setCharAt(i, letter);
            }
        }
    }

    public void displayHangmanWordLength() {

        if (remainingTries == 10) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("______");
            System.out.println();
        }

        if (remainingTries == 9) {
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
            System.out.println();
        }

        if (remainingTries == 8) {
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
            System.out.println();
        }

        if (remainingTries == 7) {
            System.out.println("   ___");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
            System.out.println();
        }

        if (remainingTries == 6) {
            System.out.println("   ______");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
            System.out.println();
        }

        if (remainingTries == 5) {
            System.out.println("   ____________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
            System.out.println();
        }

        if (remainingTries == 4) {
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
            System.out.println();
        }

        if (remainingTries == 3) {
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |");
            System.out.println("___|___");
            System.out.println();
        }

        if (remainingTries == 2) {
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
            System.out.println();
        }

        if (remainingTries == 1) {
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |          _|_");
            System.out.println("   |         / | \\");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
            System.out.println();
        }
    }
}