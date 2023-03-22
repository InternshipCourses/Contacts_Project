package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacts {
    private Person person;
    private String phoneNumber;


    public Contacts() {
        this.person = new Person();
        this.phoneNumber = "";
    }

    public Contacts(Person person, String phoneNumber) {
        this.person = person;
        setPhoneNumber(phoneNumber);
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
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
    }

    private boolean validatePhoneNumber(String phoneNumber) {


        // no group with brackets
        String regex1 = "([+]?\\w?([\\s-]?\\w{2,})*)";

        // the first group has brackets
        String regex2 = "([+]?(\\(\\w+\\))([\\s-]\\w{2,})*)";

        // the second group has brackets
        String regex3 = "([+]?\\w+[\\s-]\\(\\w{2,}\\)([\\s-]\\w{2,})*)";

        Pattern pattern = Pattern.compile(regex1 + "|" + regex2 + "|"+ regex3);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }


}
