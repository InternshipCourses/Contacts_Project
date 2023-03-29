package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private static List<ContactDetails> phoneBook;

    static {
        phoneBook = new ArrayList<>();
    }

    public PhoneBook() {

    }
    public PhoneBook(List<ContactDetails> newPhonebook) {
        phoneBook = newPhonebook;
    }

    public boolean hasNumber(int index){
        return !phoneBook.isEmpty() &&
                !phoneBook.get(index).getPhoneNumber().equals("[no number]");
    }
    public void listContact() {
        if (phoneBook.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            int count = 1;
            for (ContactDetails contact : phoneBook) {
                if (Boolean.TRUE.equals(contact.getIsPerson())) {
                    System.out.println(displayUserInfo(count,(Person) contact));
                } else {
                    System.out.println(displayOrganizationInfo(count, (Organization) contact));
                }
                count++;
            }
        }
    }

    private String displayUserInfo(int count, Person person){
        return String.format("%s. %s %s",count,person.getFirstName(),person.getLastName());
    }
    private String displayOrganizationInfo(int count, Organization organization){
        return String.format("%s. %s",count,organization.getOrganizationName());
    }

    public void contactInformation(int index){
        String result= "";
        if (Boolean.TRUE.equals(phoneBook.get(index).getIsPerson())) {
            Person person = (Person) phoneBook.get(index);
            result = String.format("""
                        Name: %s
                        Surname: %s
                        Birth date: %s
                        Gender: %s
                        Number: %s
                        Time created: %s
                        Time last edit: %s""",person.getFirstName(),person.getLastName(),
                    person.getDateOfBirth(), person.getGender(),person.getPhoneNumber(),
                    person.getTimeCreated(),person.getTimeLastEdit());
        }else {
            Organization organization = (Organization) phoneBook.get(index);
            result = String.format("""
                        Organization name: %s
                        Address: %s
                        Number: %s
                        Time created: %s
                        Time last edit: %s""",organization.getOrganizationName(),
                    organization.getAddress(), organization.getPhoneNumber(),
                    organization.getTimeCreated(),organization.getTimeLastEdit());
        }
        System.out.println(result);
    }

    public void editContactFirstname(int index, String firstName){
        Person contact = (Person) phoneBook.get(index);
        contact.setFirstName(firstName);
        phoneBook.set(index,contact);
    }

    public void editContactLastname(int index, String lastName){
        Person tempPerson = (Person) phoneBook.get(index);
        tempPerson.setLastName(lastName);
        phoneBook.set(index,tempPerson);
    }
    public void editContactGender(int index, String gender){
        Person tempPerson = (Person) phoneBook.get(index);
        tempPerson.setGender(gender);
        phoneBook.set(index,tempPerson);
    }

    public void editContactBirthday(int index, String birthday){
        Person tempPerson = (Person) phoneBook.get(index);
        tempPerson.setDateOfBirth(birthday);
        phoneBook.set(index,tempPerson);
    }

    public void editOrganizationName(int index, String newName){
        Organization tempOrganization = (Organization) phoneBook.get(index);
        tempOrganization.setOrganizationName(newName);
        phoneBook.set(index,tempOrganization);
    }

    public void editOrganizationAddress(int index, String newAddress){
        Organization tempOrganization = (Organization) phoneBook.get(index);
        tempOrganization.setAddress(newAddress);
        phoneBook.set(index,tempOrganization);
    }

     public void editContactPhoneNumber(int index, String phoneNumber){
        phoneBook.get(index).setPhoneNumber(phoneNumber);
     }

    public void saveNewContact(ContactDetails contacts){
       phoneBook.add(contacts);
       System.out.println("The record added.");
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

   public boolean isPerson(int index){
       return phoneBook.get(index).getIsPerson();
   }
}
