package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private static List<Contacts> phoneBook;

    static {
        phoneBook = new ArrayList<>();
    }



    public PhoneBook() {

    }
    public PhoneBook(List<Contacts> phonebook) {
        phoneBook = phonebook;
    }

    public boolean hasNumber(int index){
        if (!phoneBook.isEmpty()){
           if (!phoneBook.get(index).getPhoneNumber().equals("[no number]")) {
               return true;
           }
        }
        return false;
    }
    public void listContact() {
        if (phoneBook.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            int count = 1;
            for (Contacts contact : phoneBook) {
                System.out.println(count + ". " + contact.getPerson().getFirstName() + " " +
                contact.getPerson().getLastName() + ", " +contact.getPhoneNumber());
                count++;
            }
        }
    }

    public void editContactFirstname(int index, String firstName){
        String lastName = phoneBook.get(index).getPerson().getLastName();
        phoneBook.get(index).setPerson(new Person(firstName, lastName));
    }
    public void editContactLastname(int index, String lastName){
        String firstName = phoneBook.get(index).getPerson().getFirstName();
        phoneBook.get(index).setPerson(new Person(firstName, lastName));
    }
     public void editContactPhoneNumber(int index, String phoneNumber){

        phoneBook.get(index).setPhoneNumber(phoneNumber);
     }
    public void saveNewContact(Contacts contacts){
       phoneBook.add(contacts);
       System.out.println("The record added.");
   }


   public void removeContact(int index) {
        if (!phoneBook.isEmpty() ) {
            phoneBook.remove(index);
            System.out.println("The record removed!");
        } else {
            System.out.println("No records to remove!");
        }
   }

   public int countContact() {
        return phoneBook.size();
   }

}
