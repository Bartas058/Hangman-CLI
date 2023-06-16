package org.example;

public abstract class AbstractStatsMenu extends LocalPath { // Abstract class
    abstract void displayLocalTime();   // Abstract method

    private final FilePath filePath = () -> {   // Anonymous inner class
        String localPath = System.getProperty("user.dir");
        System.out.println("Local programme pathway: " + localPath);
        System.out.println("---------------");
        System.out.println("The author of the game thanks you for taking the time to play!");
    };

    public FilePath getFilePath() {
        return filePath;
    }
}