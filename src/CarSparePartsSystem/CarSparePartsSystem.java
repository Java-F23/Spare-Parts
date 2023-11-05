package CarSparePartsSystem;

import java.util.*;
import java.util.stream.Collectors;

public class CarSparePartsSystem {

    private final List<SparePart> sparePartsInventory = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final String adminPassword = "admin123"; // Simple hardcoded password for demonstration

    public static void main(String[] args) {
        CarSparePartsSystem system = new CarSparePartsSystem();
        system.run();
    }

    private void run() {
        System.out.println("Welcome to the Car Spare Parts Management System");
        System.out.println("Are you an Admin or a User?");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.print("Choose an option: ");

        int userType = scanner.nextInt();
        if (userType == 1) {
            // Admin login
            adminLogin();
        } else {
            // User functionalities
            userMenu();
        }
    }

    private void adminLogin() {
        System.out.print("Enter Admin Password: ");
        String inputPassword = scanner.next();

        if (adminPassword.equals(inputPassword)) {
            adminMenu();
        } else {
            System.out.println("Incorrect password. Access denied.");
        }
    }

    private void adminMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Spare Part");
            System.out.println("2. Edit Spare Part");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    addSparePart();
                    break;
                case 2:
                    editSparePart();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void userMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("User Menu:");
            System.out.println("1. View Spare Parts");
            System.out.println("2. Search Spare Parts");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    viewSpareParts();
                    break;
                case 2:
                    searchSpareParts();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addSparePart() {
        System.out.println("Enter spare part name:");
        String name = scanner.next();

        System.out.println("Enter spare part type:");
        String type = scanner.next();

        System.out.println("Enter spare part specifications:");
        String specifications = scanner.next();

        SparePart newSparePart = new SparePart(name, type, specifications);
        sparePartsInventory.add(newSparePart);
        System.out.println("Spare part added successfully!");
    }

    private void viewSpareParts() {
        if (sparePartsInventory.isEmpty()) {
            System.out.println("No spare parts available.");
        } else {
            sparePartsInventory.forEach(System.out::println);
        }
    }

    private void searchSpareParts() {
        System.out.println("Enter search type:");
        String type = scanner.next();

        List<SparePart> results = sparePartsInventory.stream()
                .filter(sparePart -> sparePart.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No spare parts found for type: " + type);
        } else {
            results.forEach(System.out::println);
        }
    }

    private void editSparePart() {
        System.out.println("Enter spare part name to edit:");
        String name = scanner.next();

        Optional<SparePart> sparePartOptional = sparePartsInventory.stream()
                .filter(sparePart -> sparePart.getName().equalsIgnoreCase(name))
                .findFirst();

        if (sparePartOptional.isPresent()) {
            SparePart sparePart = sparePartOptional.get();

            System.out.println("Enter new type:");
            sparePart.setType(scanner.next());

            System.out.println("Enter new specifications:");
            sparePart.setSpecifications(scanner.next());

            System.out.println("Spare part updated successfully!");
        } else {
            System.out.println("Spare part not found: " + name);
        }
    }

    static class SparePart {
        private String name;
        private String type;
        private String specifications;

        public SparePart(String name, String type, String specifications) {
            this.name = name;
            this.type = type;
            this.specifications = specifications;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSpecifications() {
            return specifications;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

        @Override
        public String toString() {
            return "SparePart{" +
                    "name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", specifications='" + specifications + '\'' +
                    '}';
        }
    }
}
