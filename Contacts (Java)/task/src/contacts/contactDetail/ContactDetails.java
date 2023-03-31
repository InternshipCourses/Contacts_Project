package contacts.contactDetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ContactDetails implements Serializable {
    private String phoneNumber;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;
    private static final long serialVersionUID = 7L;

    protected ContactDetails(String contactNumber) {
        this.phoneNumber = contactNumber;
        this.timeCreated = LocalDateTime.now().withNano(0);
        this.timeLastEdit = this.timeCreated;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    protected void setPhoneNumber(String newPhoneNumber) {
        if (validatePhoneNumber(newPhoneNumber)) {
            this.phoneNumber = newPhoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
    }

    protected boolean validatePhoneNumber(String newPhoneNumber) {
        // no group with brackets
        String regex1 = "([+]?\\w?([\\s-]?\\w{2,})*)";
        // the first group has brackets
        String regex2 = "([+]?(\\(\\w+\\))([\\s-]\\w{2,})+)";
        // the second group has brackets
        String regex3 = "([+]?\\w+[\\s-]\\(\\w{2,}\\)([\\s-]\\w{2,})+)";
        Pattern pattern = Pattern.compile(regex1 + "|" + regex2 + "|"+ regex3);
        Matcher matcher = pattern.matcher(newPhoneNumber);
        return matcher.matches();
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime newTime) {
        this.timeCreated = newTime;
    }

    public LocalDateTime getTimeLastEdit() {
        return timeLastEdit;
    }

    public void setTimeLastEdit(LocalDateTime newEditedTime) {
        this.timeLastEdit = newEditedTime;
    }

    // todo remove the reader if able to read from console another way
    public  abstract ContactDetails addNewContact(BufferedReader reader) throws IOException;


    public abstract String showContactInformation();
    public abstract String showBasicInformation();

    public abstract ContactDetails editUserInformation(BufferedReader reader) throws IOException;

}
