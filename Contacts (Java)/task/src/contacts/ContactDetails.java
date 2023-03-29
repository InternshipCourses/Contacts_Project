package contacts;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class ContactDetails {
    protected String phoneNumber;
    private final Boolean isPerson;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;

    protected ContactDetails(String contactNumber,Boolean isThisAPerson) {
        this.phoneNumber = contactNumber;
        this.isPerson = isThisAPerson;
        this.timeCreated = LocalDateTime.now().withNano(0);
        this.timeLastEdit = this.timeCreated;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    protected void setPhoneNumber(String phoneNumber) {
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
    }

    protected Boolean getIsPerson() {
        return isPerson;
    }

    protected boolean validatePhoneNumber(String phoneNumber) {
        // no group with brackets
        String regex1 = "([+]?\\w?([\\s-]?\\w{2,})*)";
        // the first group has brackets
        String regex2 = "([+]?(\\(\\w+\\))([\\s-]\\w{2,})+)";
        // the second group has brackets
        String regex3 = "([+]?\\w+[\\s-]\\(\\w{2,}\\)([\\s-]\\w{2,})+)";
        Pattern pattern = Pattern.compile(regex1 + "|" + regex2 + "|"+ regex3);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeLastEdit() {
        return timeLastEdit;
    }

    public void setTimeLastEdit(LocalDateTime timeLastEdit) {
        this.timeLastEdit = timeLastEdit;
    }
}
