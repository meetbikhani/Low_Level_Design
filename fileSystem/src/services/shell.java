
package services;

import java.util.Scanner;

public class shell {
    filesystem filesystem;
    Boolean isRunning;
    Scanner scanner;

    public shell(filesystem filesystem) {
        this.filesystem = filesystem;
        this.isRunning = true;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (isRunning) {
            System.out.print("Enter command: ");

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+");

            String command = parts[0];

            if (command.equals("mkdir") && parts.length > 1) {
                filesystem.mkdir(parts[1]);

            } else if (command.equals("cd") && parts.length > 1) {
                filesystem.cd(parts[1]);

            } else if (command.equals("ls")) {
                filesystem.ls();

            } else if (command.equals("pwd")) {
                filesystem.pwd();

            } else if (command.equals("touch") && parts.length > 1) {
                filesystem.touch(parts[1]);

            } else if (command.equals("exit")) {
                isRunning = false;

            } else {
                System.out.println("Invalid command or missing argument");
            }
        }

        scanner.close();
    }
}
