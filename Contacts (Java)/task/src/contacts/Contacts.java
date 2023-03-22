package contacts;

public class Contacts {
    private Person person;
    private String phoneNumber;


    public Contacts() {
        this.person = new Person();
        this.phoneNumber = "";
    }

    public Contacts(Person person, String phoneNumber) {
        this.person = person;
        this.phoneNumber = phoneNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
