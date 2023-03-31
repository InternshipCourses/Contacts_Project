package contacts;

import contacts.contactDetail.ContactDetails;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook implements Serializable {
    private static List<ContactDetails> phoneBook;

    static {
        phoneBook = new ArrayList<>();
    }
    private static final long serialVersionUID = 7L;

    public PhoneBook() {

    }
    public PhoneBook(List<ContactDetails> newPhonebook) {
        phoneBook = newPhonebook;
    }

    public  List<ContactDetails> getPhoneBook() {
        return this.phoneBook;
    }

    public void setPhoneBook(List<ContactDetails> newPhoneBook) {
       this.phoneBook = newPhoneBook;
    }

    public void addContactToPhoneBook(List<ContactDetails> newPhoneBook){
        phoneBook.addAll(newPhoneBook);
    }

    public void updatePhoneBookContact(ContactDetails updatedContact) {
        phoneBook.set(phoneBook.indexOf(updatedContact),updatedContact);
    }

    public boolean hasNumber(int index){
        return !phoneBook.isEmpty() &&
                !phoneBook.get(index).getPhoneNumber().equals("[no number]");
    }

    public void listContact() {
        if (phoneBook.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            phoneBook.forEach(item -> System.out.printf("%d. %s %n",phoneBook.indexOf(item) + 1,item.showBasicInformation()));
        }
    }

    public void removeContact(ContactDetails selectedContact){
        phoneBook.remove(phoneBook.indexOf(selectedContact));
    }

    public void saveNewContact(ContactDetails contacts){
       if (contacts != null) {
           phoneBook.add(contacts);
           System.out.println("The record added.");
       }
   }

   public List<ContactDetails> searchForContact(String searchTerm){
       Pattern pattern = Pattern.compile("\\w*" + searchTerm + "\\w*",Pattern.CASE_INSENSITIVE);
       List<ContactDetails> searchList = new ArrayList<>();
        for (ContactDetails checkedContact: phoneBook) {
            Matcher matcher = pattern.matcher(checkedContact.showContactInformation());
            if (matcher.find()){
               searchList.add(checkedContact);
            }
        }
        return searchList;
   }

   public void removeContact(int index) {
        if (!phoneBook.isEmpty()) {
            phoneBook.remove(index);
            System.out.println("The record removed!");
        } else {
            System.out.println("No records to remove!");
        }
   }

   public int countContact() {
        return phoneBook.size();
   }

   /*public boolean isPerson(int index){
       return phoneBook.get(index).getIsPerson();
   }*/
}
