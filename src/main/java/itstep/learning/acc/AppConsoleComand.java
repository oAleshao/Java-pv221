package itstep.learning.acc;

import itstep.learning.db.DbDemo;
import itstep.learning.fs.FileDemo;

import javax.inject.Inject;
import java.util.Scanner;

public class AppConsoleComand {
    @Inject
    DbDemo dbDemo = new DbDemo();
    FileDemo fileDemo = new FileDemo();
    Scanner scanner;
    String command;

    public void run(){
        showCommands();

        while (true) {
            scanner = new Scanner(System.in);
            System.out.print("> ");
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
        System.out.println("        - show db.ini");
        System.out.println("        - add string in db.ini");
    }

    private void executeCommand(String command) {
        switch (command.toLowerCase()) {
            case "show journal":
                this.dbDemo.run();
                break;
            case "show db.ini":
                this.fileDemo.showDbIni();
                break;
            case "add string in db.ini": {
                System.out.print("  >> Enter key: ");
                String key = scanner.nextLine();
                System.out.print("  >> Enter value: ");
                String value = scanner.nextLine();
                this.fileDemo.addDbIni(key, value);
                break;
            }
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}
