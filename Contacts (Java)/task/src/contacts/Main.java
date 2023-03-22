package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the person:");
        var firstName = scanner.next();
        System.out.println("Enter the surname of the person:");
        var lastName = scanner.next();
        System.out.println("Enter the number:");
        var phoneNumber = scanner.next();

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.saveNewContact(new Contacts(new Person(firstName,lastName),phoneNumber));
    }
}
