package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
   private List<Contacts> phoneBook;

    public PhoneBook() {
        this.phoneBook = new ArrayList<>();
    }
    public PhoneBook(List<Contacts> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public void saveNewContact(Contacts contacts){
       this.phoneBook.add(contacts);
       System.out.println("A record created! \nA Phone Book with a single record created!");
   }
}
