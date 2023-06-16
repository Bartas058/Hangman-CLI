package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

interface StatsMenu {       // Interface
    void menuStats();
}

public class Stats extends AbstractStatsMenu implements StatsMenu {

    private static final HashMap<String, int[]> playerStats = new HashMap<>();
    private static final HashMap<String, int[]> overallStats = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);
    private static String currentUsername;

    public static void setCurrentUsername(String currentUsername) {
        Stats.currentUsername = currentUsername;
    }

    private static class CurrentUser {      // Static class
        private static String currentUsername;

        public static String getCurrentUsername() {
            return currentUsername;
        }

        public static void setCurrentUsername(String username) {
            currentUsername = username;
        }
    }

    @Override
    public void displayLocalTime() {
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current local time: " + currentTime);
    }

    @Override
    public void menuStats() {
        boolean exitMenu = false;

        while (!exitMenu) {
            System.out.println("---------------");
            displayLocalTime();
            displayLocalPath();
            System.out.println("---------------");
            displayLocalPath("Method overloading mechanism");
            System.out.println("---------------");
            displayLocalClient();
            System.out.println("---------------");
            System.out.println("Statistics");
            System.out.println("1. Overall game statistics for this session");
            System.out.println("2. View all players' statistics - Rank");
            System.out.println("3. View the statistics of a specific player");
            System.out.println("4. Skip");
            System.out.print(">>> ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1 -> displayOverallStats();
                    case 2 -> displayPlayersStats();
                    case 3 -> {
                        System.out.println("---------------");
                        System.out.println("Enter the player's nickname:");
                        System.out.print(">>> ");
                        String nickname = scanner.nextLine();
                        displaySelectedPlayerStats(nickname);
                    }
                    case 4 -> exitMenu = true;
                    default -> System.out.println("Error: invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: invalid choice");
                scanner.nextLine();
            }
        }
    }


    public static void updatePlayerStats(String playerName, int currentWins, int currentLosses, int gamesTotal) {
        int[] stats = {currentWins, currentLosses, gamesTotal};
        playerStats.put(playerName, stats);
    }

    public static void updateOverallStats(int wins, int losses, int overallGames, int correctAnswers, int incorrectAnswers) {
        int[] stats = {wins, losses, overallGames, correctAnswers, incorrectAnswers};
        CurrentUser.setCurrentUsername(currentUsername);
        overallStats.put(CurrentUser.getCurrentUsername(), stats);
    }

    public void displayOverallStats() {
        int[] stats = overallStats.get(currentUsername);
        int wins = stats[0];    // Overall
        int losses = stats[1];  // Overall
        int overallGames = stats[2];    // Overall
        int correctAnswers = stats[3];  // Overall
        int incorrectAnswers = stats[4];    // Overall
        int answersCount = correctAnswers + incorrectAnswers;   // Overall
        double successRate = (double) correctAnswers / answersCount * 100;  // Overall
        double winPercentage = (double) wins / overallGames * 100;  // Overall

        System.out.println("---------------");
        System.out.println("Overall Statistics:");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Total Games: " + overallGames);
        System.out.printf("Win Rate: %.2f%%\n", winPercentage);
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + incorrectAnswers);
        System.out.printf("Answers Success Rate: %.2f%%\n", successRate);

        System.out.println("---------------");
        System.out.println("Would you like to save the overall statistics to a .txt file? (Y/N)");
        System.out.print(">>> ");
        String saveToFile = scanner.nextLine().trim().toUpperCase();

        while (!saveToFile.equals("Y") && !saveToFile.equals("N")) {
            if (saveToFile.isEmpty()) {
                System.out.println("---------------");
                System.out.println("Error: invalid choice. The choice cannot be empty");
            } else {
                System.out.println("---------------");
                System.out.println("Error: invalid choice");
            }

            System.out.println("Would you like to save the overall statistics to a .txt file? (Y/N)");
            System.out.print(">>> ");
            saveToFile = scanner.nextLine().trim().toUpperCase();
        }

        if (saveToFile.equals("Y")) {
            String fileName = "overallStatistics.txt";
            String filePath = "data/" + fileName;

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write("Overall Statistics:\n");
                writer.write("Wins: " + wins + "\n");
                writer.write("Losses: " + losses + "\n");
                writer.write("Total Games: " + overallGames + "\n");
                writer.write(String.format("Win Rate: %.2f%%\n", winPercentage));
                writer.write("Correct Answers: " + correctAnswers + "\n");
                writer.write("Incorrect Answers: " + incorrectAnswers + "\n");
                writer.write(String.format("Success Rate: %.2f%%\n", successRate));
                System.out.println("---------------");
                System.out.println("Overall statistics saved to file: overallStatistics.txt");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }

        } else {
            System.out.println("Overall statistics not saved to file");
        }
    }

    public void displayPlayersStats() {

        List<Map.Entry<String, int[]>> sortedStats = new ArrayList<>(playerStats.entrySet());

        System.out.println("---------------");
        System.out.println("Ranking of best players - based on victory rate");

        sortedStats.sort((a, b) -> {    // Lambda expression
            int[] statsA = a.getValue();
            int[] statsB = b.getValue();
            double winPercentageA = (double) statsA[0] / statsA[2] * 100;
            double winPercentageB = (double) statsB[0] / statsB[2] * 100;

            return Double.compare(winPercentageB, winPercentageA);
        });

        StringBuilder statsToSave = new StringBuilder();

        sortedStats.forEach(entry -> {  // Lambda expression
            String playerName = entry.getKey();
            int[] stats = entry.getValue();
            int currentWins = stats[0];
            int currentLosses = stats[1];
            int gamesTotal = stats[2];
            double winPercentage = (double) currentWins / gamesTotal * 100;

            System.out.println("---------------");
            System.out.println("Statistics for player: " + playerName);
            System.out.println("Wins: " + currentWins);
            System.out.println("Losses: " + currentLosses);
            System.out.printf("Win Rate: %.2f%%\n", winPercentage);

            statsToSave.append("---------------\n");
            statsToSave.append("Statistics for player: ").append(playerName).append("\n");
            statsToSave.append("Wins: ").append(currentWins).append("\n");
            statsToSave.append("Losses: ").append(currentLosses).append("\n");
            statsToSave.append(String.format("Win Rate: %.2f%%\n", winPercentage));
        });

        System.out.println("---------------");
        System.out.println("Would you like to save the players' statistics to a txt file? (Y/N)");
        System.out.print(">>> ");

        String saveToFile = scanner.nextLine().trim().toUpperCase();

        while (!saveToFile.equals("Y") && !saveToFile.equals("N")) {
            if (saveToFile.isEmpty()) {
                System.out.println("---------------");
                System.out.println("Error: invalid choice. The choice cannot be empty");
            } else {
                System.out.println("---------------");
                System.out.println("Error: invalid choice");
            }

            System.out.println("---------------");
            System.out.println("Would you like to save the players' statistics to a txt file? (Y/N)");
            System.out.print(">>> ");
            saveToFile = scanner.nextLine().trim().toUpperCase();
        }

        if (saveToFile.equals("Y")) {
            String fileName = "playersStatistics.txt";
            String filePath = "data/" + fileName;

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(statsToSave.toString());
                System.out.println("---------------");
                System.out.println("Statistics saved to file: playersStatistics.txt");
            } catch (IOException e) {
                System.out.println("---------------");
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("---------------");
            System.out.println("Statistics not saved to file");
        }
    }

    public void displaySelectedPlayerStats(String playerName) {
        int[] stats = playerStats.get(playerName);

        if (stats != null) {
            int currentWins = stats[0];
            int currentLosses = stats[1];
            int gamesTotal = stats[2];
            double winPercentage = (double) currentWins / gamesTotal * 100;

            System.out.println("---------------");
            System.out.println("Statistics for player: " + playerName);
            System.out.println("Wins: " + currentWins);
            System.out.println("Losses: " + currentLosses);
            System.out.printf("Win Rate: %.2f%%\n", winPercentage);

            System.out.println("---------------");
            System.out.println("Would you like to save the player's statistics to a txt file? (Y/N)");
            System.out.print(">>> ");

            String saveToFile = scanner.nextLine().trim().toUpperCase();

            while (!saveToFile.equals("Y") && !saveToFile.equals("N")) {
                if (saveToFile.isEmpty()) {
                    System.out.println("---------------");
                    System.out.println("Error: invalid choice. The choice cannot be empty");
                } else {
                    System.out.println("---------------");
                    System.out.println("Error: invalid choice");
                }

                System.out.println("---------------");
                System.out.println("Would you like to save the player's statistics to a txt file? (Y/N)");
                System.out.print(">>> ");
                saveToFile = scanner.nextLine().trim().toUpperCase();
            }

            if (saveToFile.equals("Y")) {
                String fileName = playerName + "_statistics.txt";
                String filePath = "data/" + fileName;

                try (FileWriter writer = new FileWriter(filePath)) {
                    writer.write("---------------\n");
                    writer.write("Statistics for player: " + playerName + "\n");
                    writer.write("Wins: " + currentWins + "\n");
                    writer.write("Losses: " + currentLosses + "\n");
                    writer.write(String.format("Win Rate: %.2f%%\n", winPercentage));
                    System.out.println("---------------");
                    System.out.println("Statistics saved to file: " + playerName + "statistics.txt");
                } catch (IOException e) {
                    System.out.println("---------------");
                    System.out.println("Error writing to file: " + e.getMessage());
                }
            } else {
                System.out.println("---------------");
                System.out.println("Statistics not saved to file");
            }
        } else {
            System.out.println("---------------");
            System.out.println("Player not found in the statistics");
        }
    }
}