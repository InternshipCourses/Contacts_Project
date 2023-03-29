package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person extends ContactDetails {
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private static final  String DEFAULT_NO_DATA = "[no data]";

    public Person() {
        super("",true);
        this.firstName = "";
        this.lastName = "";
        this.gender = DEFAULT_NO_DATA;
        this.dateOfBirth = DEFAULT_NO_DATA;
    }

    public Person(String firstName, String lastName,String phoneNumber,String gender,String dateOfBirth) {
        super(phoneNumber,true);
        this.firstName = firstName;
        this.lastName = lastName;
        setGender(gender);
        setDateOfBirth(dateOfBirth);
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (validateGender(gender)) {
            this.gender = gender;
        }else {
            this.gender = DEFAULT_NO_DATA;
        }

    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (validateBirthDay(dateOfBirth)) {
            this.dateOfBirth = dateOfBirth;
        }else {
            this.dateOfBirth = DEFAULT_NO_DATA;
        }
    }

    public static boolean validateGender(String gender) {
        return (gender.equals("M") || gender.equals("F"));
    }

    public static boolean validateBirthDay(String dateOfBirth){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(dateOfBirth,formatter);

            if (date.isAfter(LocalDate.now())){
                return false;
            }
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
        return true;
    }

}
