package contacts;

import contacts.contactDetail.ContactDetails;
import contacts.contactDetail.ContactFactory;
import contacts.input_output.impl.DataInputOutput;

import java.util.List;

public class ContactApp {

    private static final String INVALID_OPTION = "Invalid Option";
    private final PhoneBook phoneBook = new PhoneBook();
    private final DataInputOutput reader;
    public ContactApp(DataInputOutput inputOutput) {
        reader = inputOutput;
    }
    private String option;

    public void start(){
         loadSavedContacts(phoneBook);
            do {
                reader.printToConsole("Enter action ( add, list, search, count, exit): ");
                option = reader.readLine();
                switch (option) {
                    case "add" -> phoneBook.saveNewContact(ContactFactory.startContactFactory(reader));
                    case "list" ->   listAllContacts(phoneBook,reader);
                    case "search" -> searchForContacts(phoneBook,reader);
                    case "count" -> countContact(phoneBook);
                    case "exit" -> reader.printToConsole("GoodBye");
                    default -> reader.printToConsole(INVALID_OPTION);
                }
                reader.printToConsole("");
            } while (!option.equals("exit"));
            reader.saveObjectToFile(phoneBook);

    }

    private  void listAllContacts(PhoneBook phoneBook, DataInputOutput reader)  {
        if (phoneBook.countContact() > 0){
            phoneBook.listContact();
            reader.printToConsole("[list] Enter action ([number], back):");
            option = reader.readLine();
            try {
                if ( Integer.parseInt(option) > 0) {
                    viewEditRemoveContactMenu(phoneBook, reader, phoneBook.getPhoneBook().get(Integer.parseInt(option) - 1));
                }
            }
            catch (NumberFormatException n){
                // User entered a String like "Back" just ignore and continue
            }
        }
    }

    private  void searchForContacts(PhoneBook phoneBook, DataInputOutput reader)  {
        reader.printToConsole("Enter search query:");
        option = "";
        do {
            option = reader.readLine();
            List<ContactDetails> contactList = phoneBook.searchForContact(option);
            option = viewContacts(phoneBook, reader, contactList);
        } while (!(option.equals("back") || option.equals("menu")));
    }

    private  String viewContacts(PhoneBook phoneBook, DataInputOutput reader,
                                        List<ContactDetails> contactList) {
        if (!contactList.isEmpty()){
            reader.printToConsole(String.format("Found %d results:n", contactList.size()));
            contactList.forEach(item -> reader.printToConsole(String.format("%d. %s%n", contactList.indexOf(item) + 1,
                    item.showBasicInformation())));
            reader.printToConsole("\n[search] Enter action ([number], back, again):");
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
            reader.printToConsole("No Contact found");
        }
        return option;
    }

    private  String viewEditRemoveContactMenu(PhoneBook phoneBook, DataInputOutput reader, ContactDetails selectedContact){
        reader.printToConsole(selectedContact.showContactInformation() + "\n"); // display contact info
        do {
            reader.printToConsole("[record] Enter action (edit, delete, menu):"); // menu
            option = reader.readLine();
            if (option.equals("edit")) {
                editContactInformation(phoneBook, selectedContact, reader);
            } else if (option.equals("delete")) {
                phoneBook.removeContact(selectedContact);
            }
        } while (!option.equals("menu"));
        return option;
    }


    private  void loadSavedContacts(PhoneBook phoneBook)  {
        phoneBook.addContactToPhoneBook((List<? extends ContactDetails>) reader.readObjectToFile());
    }

    private  void countContact(PhoneBook phoneBook) {
        if (phoneBook.countContact() > 0) {
            reader.printToConsole(String.format("%d",phoneBook.countContact()));
        } else {
            reader.printToConsole("The Phone Book has 0 records.");
        }
    }

    private  void editContactInformation(PhoneBook phoneBook, ContactDetails contactDetails, DataInputOutput reader) {
        contactDetails.showContactInformation();
        //pass contact detail to phoneBook class where the update values will be updated in the contact
        phoneBook.updatePhoneBookContact(contactDetails.editUserInformation(reader));
        contactDetails.showContactInformation();
    }

}


