package org.example;

@FunctionalInterface
interface FilePath {
    void displayLocalPath();

    default void displayLocalPath(String message) {     // Method overloading mechanism
        System.out.println(message);
        displayLocalPath();
    }
}

public class LocalPath extends LocationResolver implements FilePath {
    public void displayLocalPath() {
        String localPath = System.getProperty("user.dir");
        System.out.println("Local programme pathway: " + localPath);
        System.out.println("---------------");
        System.out.println("The author of the game thanks you for taking the time to play!");
    }
}