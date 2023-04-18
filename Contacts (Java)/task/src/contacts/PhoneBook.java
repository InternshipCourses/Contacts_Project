package contacts;

import contacts.contactDetail.ContactDetails;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook implements Serializable {
    private static final  List<ContactDetails> PHONE_BOOK;
    private final transient Logger logger = Logger.getLogger(PhoneBook.class.getName());

    static {
        PHONE_BOOK = new ArrayList<>();
    }
    @Serial
    private static final long serialVersionUID = 7L;


    public  List<ContactDetails> getPhoneBook() {
        return PHONE_BOOK;
    }

    public void addContactToPhoneBook(List<? extends ContactDetails> newPhoneBook){
        if (newPhoneBook == null) {
            return;
        }
        PHONE_BOOK.addAll(newPhoneBook);
    }

    public void updatePhoneBookContact(ContactDetails updatedContact) {
        PHONE_BOOK.set(PHONE_BOOK.indexOf(updatedContact),updatedContact);
    }

    public void listContact() {
        if (PHONE_BOOK.isEmpty()) {
            logger.info("The Phone Book has 0 records.");
        } else {
            PHONE_BOOK.forEach(item -> logger.info(String.format("%d. %s %n", PHONE_BOOK.indexOf(item) + 1,item.showBasicInformation())));
        }
    }

    public void removeContact(ContactDetails selectedContact){
        PHONE_BOOK.remove(selectedContact);
    }

    public void saveNewContact(ContactDetails contacts){
       if (contacts != null) {
           PHONE_BOOK.add(contacts);
           logger.info("The record added.");
       }
   }

   public List<ContactDetails> searchForContact(String searchTerm){
       Pattern pattern = Pattern.compile("\\w*" + searchTerm + "\\w*",Pattern.CASE_INSENSITIVE);
       List<ContactDetails> searchList = new ArrayList<>();
        for (ContactDetails checkedContact: PHONE_BOOK) {
            Matcher matcher = pattern.matcher(checkedContact.showContactInformation());
            if (matcher.find()){
               searchList.add(checkedContact);
            }
        }
        return searchList;
   }

   public int countContact() {
        return PHONE_BOOK.size();
   }
}
