package contacts;

import contacts.contactDetail.ContactDetails;
import contacts.contactDetail.ContactFactory;

import java.io.*;
import java.util.List;

public class Main {

    private static String invalidOption = "Invalid Option";

 /*   private final static List<String> stringStream;

    static {
        stringStream = new BufferedReader(new InputStreamReader(System.in))
                .lines()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

    }

    Supplier<Stream<String>> stringSupplier = new Supplier<>() {
        @Override
        public Stream<String> get() {
            return stringStream.stream();
        }

    };*/

    private static  String savedFileLocation = "";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length > 0) {savedFileLocation = args[0];} // checking if a saved file location was passed when starting the app
        String option = "";
        final PhoneBook phoneBook = new PhoneBook();
        loadSavedContacts(phoneBook);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            do {
                System.out.println("Enter action ( add, list, search, count, exit): ");
                option = reader.readLine();
                switch (option) {
                    case "add" -> phoneBook.saveNewContact(ContactFactory.startContactFactory(reader));
                    case "list" ->   listAllContacts(phoneBook,reader);
                    case "search" -> searchForContacts(phoneBook,reader);
                    case "count" -> countContact(phoneBook);
                    case "exit" -> System.out.println("GoodBye");
                    default -> System.out.println(invalidOption);
                }
                System.out.println();
            } while (!option.equals("exit"));
            savedContactToFile(phoneBook);
        }
    }

    private static void listAllContacts(PhoneBook phoneBook, BufferedReader reader) throws IOException {
        if (phoneBook.countContact() > 0){
            phoneBook.listContact();
            System.out.println("[list] Enter action ([number], back):");
            String option = reader.readLine();
            try {
                if ( Integer.parseInt(option) > 0) {
                    viewEditRemoveContactMenu(phoneBook, reader, phoneBook.getPhoneBook().get(Integer.parseInt(option) - 1));
                }
            } catch (NumberFormatException n){
                // User entered a String like "Back" just ignore and continue
            }
        }
    }

    private static void searchForContacts(PhoneBook phoneBook, BufferedReader reader) throws IOException {
        System.out.println("Enter search query:");
        String option = "";
        do {
            option = reader.readLine();
            List<ContactDetails> contactList = phoneBook.searchForContact(option);
            option = viewContacts(phoneBook, reader, option, contactList);
        } while (!(option.equals("back") || option.equals("menu")));
    }

    private static String viewContacts(PhoneBook phoneBook, BufferedReader reader,
                                       String option, List<ContactDetails> contactList) throws IOException {
        if (!contactList.isEmpty()){
            System.out.printf("Found %d results:%n", contactList.size());
            contactList.forEach(item -> System.out.printf("%d. %s%n", contactList.indexOf(item) + 1,
                    item.showBasicInformation()));
            System.out.println("\n[search] Enter action ([number], back, again):");
            option = reader.readLine();
           try {
               if (Integer.parseInt(option) > 0) {
                   // selected Contact can be view edit and remove
                   option = viewEditRemoveContactMenu(phoneBook, reader, contactList.get(Integer.parseInt(option)-1));
               }
           } catch (NumberFormatException n){
               // User entered a String like "Back" just ignore and continue
           }
        } else {
            System.out.println("No Contact found");
        }
        return option;
    }

    private static String viewEditRemoveContactMenu(PhoneBook phoneBook, BufferedReader reader,
                                                    ContactDetails selectedContact) throws IOException {
        System.out.println(selectedContact.showContactInformation() + "\n"); // display contact info
        String option ="";
        do {
        System.out.println("[record] Enter action (edit, delete, menu):"); // menu
         option = reader.readLine();
            if (option.equals("edit")) {
                editContactInformation(phoneBook, selectedContact, reader);
            } else if (option.equals("delete")) {
                phoneBook.removeContact(selectedContact);
            }
            System.out.println("");
        } while (!option.equals("menu"));
        return option;
    }

    private static void savedContactToFile(PhoneBook phoneBook) throws IOException {
        if (!savedFileLocation.equals("")) {
            try (ObjectOutputStream objectOutputStream =
                         new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(savedFileLocation)))) {
                    objectOutputStream.writeObject(phoneBook.getPhoneBook());
            }
            System.out.println("Contacts Saved to Database");
        }
    }

    private static void loadSavedContacts(PhoneBook phoneBook) throws IOException, ClassNotFoundException {
        if (!savedFileLocation.equals("")) {
            try (ObjectInputStream objectInputStream =
                         new ObjectInputStream(new BufferedInputStream(new FileInputStream(savedFileLocation)))) {
                phoneBook.addContactToPhoneBook((List<ContactDetails>) objectInputStream.readObject());
                System.out.println("Contacts loaded from database file");
            } catch (InvalidClassException exception) {
                System.out.println("unsuccessfully import of contact");
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Contact database cannot be found " + fileNotFoundException);
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

    private static void editContactInformation(PhoneBook phoneBook,ContactDetails contactDetails, BufferedReader reader) throws IOException {
        contactDetails.showContactInformation();
        //pass contact detail to phoneBook class where the update values will be updated in the contact
        phoneBook.updatePhoneBookContact(contactDetails.editUserInformation(reader));
        contactDetails.showContactInformation();
    }
}
