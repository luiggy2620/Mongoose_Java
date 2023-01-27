import database.Operations.UserOperations;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserOperations userOperations = UserOperations.getUserOperations();

        boolean isRunning = true;
        while (isRunning) {
            System.out.print("\nChoose the option:\n1. Show users\n2. Show users ascending" +
                    "\n3. Show users descending\n\nOther. Stop\n--> ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    userOperations.find().forEachRemaining(user -> {
                        System.out.println(user.toJson());
                    });
                    break;
                case 2:
                    userOperations.findAndSorter("name", true)
                            .forEachRemaining(user -> {
                        System.out.println(user.toJson());
                    });
                    break;
                case 3:
                    userOperations.findAndSorter("name", false)
                            .forEachRemaining(user -> {
                        System.out.println(user.toJson());
                    });
                    break;
                default:
                    isRunning = false;

            }
        }

    }
}