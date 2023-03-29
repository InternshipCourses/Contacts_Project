package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacts {
    private Person person;
    private Organization organization;

    public Contacts() {
        this.person = new Person();
    }

    public Contacts(Person person) {
        this.person = person;

        setPhoneNumber(person.getPhoneNumber());
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPhoneNumber(String phoneNumber) {
        /*if (validatePhoneNumber(phoneNumber)) {
            this.person.setPhoneNumber(phoneNumber);
        } else {
            this.person.setPhoneNumber("[no number]");
            System.out.println("Wrong number format!");
        }*/
    }



}
