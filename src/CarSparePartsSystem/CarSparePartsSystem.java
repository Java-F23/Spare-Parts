package CarSparePartsSystem;

import java.util.Scanner;

public class CarSparePartsSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        OrderManager orderManager = new OrderManager();
        User user = new User(inventory);
        Admin admin = new Admin(inventory, orderManager);

        while (true) {
            System.out.println("Welcome to the Car Spare Parts System");
            System.out.println("1. Enter User Mode");
            System.out.println("2. Enter Admin Mode");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    user.userMode();
                    break;
                case 2:
                    admin.adminMode();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
