package contacts;

import java.time.LocalDate;

public class Person extends ContactDetails {
    private String firstName;
    private String lastName;
    private Character gender;
    private LocalDate dateOfBirth;


    //Todo add gender and birthday
    public Person() {
        super("",true);
        this.firstName = "";
        this.lastName = "";
    }

    public Person(String firstName, String lastName,String phoneNumber) {
        super(phoneNumber,true);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber(){
        return super.phoneNumber;
    }
    public void setPhoneNumber(String number){
        super.phoneNumber = number;
    }


}
