package contacts;

abstract class ContactDetails {
    protected String phoneNumber;
    final private Boolean isPerson;
    public ContactDetails(String contactNumber,Boolean isThisAPerson) {
        this.phoneNumber = contactNumber;
        this.isPerson = isThisAPerson;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsPerson() {
        return isPerson;
    }

}
