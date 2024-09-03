package itstep.learning.acc;

import itstep.learning.db.DbDemo;

import javax.inject.Inject;
import java.util.Scanner;

public class AppConsoleComand {
    @Inject
    DbDemo dbDemo = new DbDemo();
    Scanner scanner;
    String command;

    public void run(){
        showCommands();

        while (true) {
            scanner = new Scanner(System.in);
            System.out.print("> "); // This is the prompt
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the application.");
                break;
            }

            executeCommand(command);
        }

        scanner.close();
    }

    private static void showCommands(){
        System.out.println("Commands:");
        System.out.println("        - show journal");
    }

    private void executeCommand(String command) {
        switch (command.toLowerCase()) {
            case "show journal":
                this.dbDemo.run();
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}
