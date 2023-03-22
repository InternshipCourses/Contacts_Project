package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;
        do {

            System.out.println("Enter action (add, remove, edit, count, list, exit): ");
            option = scanner.nextLine();

            PhoneBook phoneBook = new PhoneBook();

            switch (option) {
                case "add" -> addNewContact(scanner, phoneBook);
                case "edit" -> option = editContactInformation(scanner, option, phoneBook);
                case "count" -> countContact(phoneBook);
                case "remove" -> removeContact(scanner, phoneBook);
                case "list" -> phoneBook.listContact();
            }

        } while (!option.equals("exit"));
        scanner.close();
    }

    private static void countContact(PhoneBook phoneBook) {
        if (phoneBook.countContact() > 0) {
            System.out.println(phoneBook.countContact());
        } else {
            System.out.println("The Phone Book has 0 records.");
        }
    }

    private static void addNewContact(Scanner scanner, PhoneBook phoneBook) {
        System.out.println("Enter the name of the person:");
        var firstName = scanner.nextLine();
        System.out.println("Enter the surname of the person:");
        var lastName = scanner.nextLine();
        System.out.println("Enter the number:");
        var phoneNumber = scanner.nextLine();
        phoneBook.saveNewContact(new Contacts(new Person(firstName,lastName),phoneNumber));
    }

    private static String editContactInformation(Scanner scanner, String option, PhoneBook phoneBook) {
        if (phoneBook.countContact() > 0) {
            phoneBook.listContact();
            System.out.println("Select a record:");
            int recordSelection = scanner.nextInt() - 1;
            scanner.nextLine();
            System.out.println("Select a field (name, surname, number):");
            option = scanner.nextLine();

            if (option.equals("name")) {
                System.out.println("Enter name");
                phoneBook.editContactFirstname(recordSelection, scanner.nextLine());
            } else if (option.equals("surname")) {
                System.out.println("Enter surname");
                phoneBook.editContactLastname(recordSelection, scanner.nextLine());
            } else if (option.equals("number")) {
                System.out.println("Enter number");
                phoneBook.editContactPhoneNumber(recordSelection, scanner.nextLine());
            }
        } else {
            System.out.println("No records to edit");
        }
        return option;
    }

    private static void removeContact(Scanner scanner, PhoneBook phoneBook) {
        if (phoneBook.countContact() > 0) {
            phoneBook.listContact();
            System.out.println("Select a record:");
            int removeIndex = scanner.nextInt() - 1;
            if (removeIndex >= 0 && removeIndex < phoneBook.countContact()) {
                phoneBook.removeContact(removeIndex);
            }
        } else {
            System.out.println("No records to remove!");
        }
    }
}
