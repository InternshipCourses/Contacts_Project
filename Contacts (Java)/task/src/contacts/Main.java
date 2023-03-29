package contacts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String invalidOption = "Invalid Option";
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String option;
        PhoneBook phoneBook = new PhoneBook();
        do {
            System.out.println("Enter action (add, remove, edit, count, info, exit): ");
            option = reader.readLine();
            switch (option.trim()) {
                case "add" -> addNewContact(phoneBook);
                case "edit" -> editContactInformation(option, phoneBook);
                case "count" -> countContact(phoneBook);
                case "remove" -> removeContact(phoneBook);
                case "info" -> getContactInfo(phoneBook);
                default -> System.out.println(invalidOption);
            }
            System.out.println();
        } while (!option.equals("exit"));
        reader.close();
    }

    private static void getContactInfo(PhoneBook phoneBook) throws IOException {
        phoneBook.listContact();
        if (phoneBook.countContact() > 0) {
            System.out.println("Enter index to show info:");
            int index = Integer.parseInt(reader.readLine()) - 1;
            try {
                phoneBook.contactInformation(index);
            } catch (IndexOutOfBoundsException outOfBoundsException) {
                System.out.println("Invalid Contact");
            }
        }
    }

    private static void countContact(PhoneBook phoneBook) {
        if (phoneBook.countContact() > 0) {
            System.out.println(phoneBook.countContact());
        } else {
            System.out.println("The Phone Book has 0 records.");
        }
    }

    private static void addNewContact(PhoneBook phoneBook) throws IOException {
        System.out.println("Enter the type (person, organization):");
        switch (reader.readLine()) {
            case "person" -> {
                System.out.println("Enter the name of the person:");
                var firstName = reader.readLine();
                System.out.println("Enter the surname of the person:");
                var lastName = reader.readLine();
                System.out.println("Enter the birth date:");
                var dateOfBirth = reader.readLine();
                if (!Person.validateBirthDay(dateOfBirth)) {
                    System.out.println("Bad birth date!");
                }
                System.out.println("Enter the gender (M, F):");
                var gender = reader.readLine().toUpperCase();
                if (!Person.validateGender(gender)){
                    System.out.println("Bad gender!");
                }
                System.out.println("Enter the number:");
                var phoneNumber = reader.readLine();

                phoneBook.saveNewContact(new Person(firstName,lastName,phoneNumber,gender,dateOfBirth));
            }
            case "organization" -> {
                System.out.println("Enter the organization name:");
                var organizationName = reader.readLine();
                System.out.println("Enter the address:");
                var address = reader.readLine();
                System.out.println("Enter the number:");
                var phoneNumber = reader.readLine();
                phoneBook.saveNewContact(new Organization(organizationName,address,phoneNumber));
            }
            default -> System.out.println(invalidOption);
        }
    }

    private static String editContactInformation(String option, PhoneBook phoneBook) throws IOException {
        if (phoneBook.countContact() > 0) {
            phoneBook.listContact();
            System.out.println("Select a record:");
            int recordSelection = Integer.parseInt(reader.readLine()) - 1;
            if (phoneBook.isPerson(recordSelection)) {
                editAPersonContactInformation(phoneBook, recordSelection);
            } else {
                editAOrganizationContactInformation(phoneBook, recordSelection);
            }
        } else {
            System.out.println("No records to edit");
        }
        return option;
    }

    private static void editAOrganizationContactInformation(PhoneBook phoneBook, int recordSelection) throws IOException {
        String option;
        System.out.println("Select a field (name,address,number):");
        option = reader.readLine();
        switch (option){
            case "name" -> {
                System.out.println("Enter name");
                phoneBook.editOrganizationName(recordSelection, reader.readLine());
            }
            case "address" -> {
                System.out.println("Enter address");
                phoneBook.editOrganizationAddress(recordSelection, reader.readLine());
            }
            case "number" -> {
                System.out.println("Enter phone number");
                phoneBook.editContactPhoneNumber(recordSelection, reader.readLine());
            }
            default -> System.out.println(invalidOption);
        }
    }

    private static void editAPersonContactInformation(PhoneBook phoneBook, int recordSelection) throws IOException {
        String option;
        System.out.println("Select a field (name, surname, number,gender,birth day):");
        option = reader.readLine();

        switch (option) {
            case "name" -> {
                System.out.println("Enter name");
                phoneBook.editContactFirstname(recordSelection, reader.readLine());
            }
            case "surname" -> {
                System.out.println("Enter surname");
                phoneBook.editContactLastname(recordSelection, reader.readLine());
            }
            case "number" -> {
                System.out.println("Enter number");
                phoneBook.editContactPhoneNumber(recordSelection, reader.readLine());
            }
            case "gender" -> {
                System.out.println("Enter gender");
                phoneBook.editContactGender(recordSelection,reader.readLine());
            }
            case "birth day" -> {
                System.out.println("Enter birthday");
                phoneBook.editContactBirthday(recordSelection,reader.readLine());
            }
            default -> System.out.println(invalidOption);
        }

    }

    private static void removeContact(PhoneBook phoneBook) throws IOException {
        if (phoneBook.countContact() > 0) {
            phoneBook.listContact();
            System.out.println("Select a record:");
            int removeIndex = Integer.parseInt(reader.readLine())- 1;
            if (removeIndex >= 0 && removeIndex < phoneBook.countContact()) {
                phoneBook.removeContact(removeIndex);
            }
        } else {
            System.out.println("No records to remove!");
        }
    }
}
