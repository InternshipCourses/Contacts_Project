package contacts.concreteContactClass;

import contacts.InputOutputData;
import contacts.contactDetail.ContactDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person extends ContactDetails  {
    private static final  String DEFAULT_NO_DATA = "[no data]";
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;

    public Person() {
        super("");
        this.firstName = "";
        this.lastName = "";
        this.gender = DEFAULT_NO_DATA;
        this.dateOfBirth = DEFAULT_NO_DATA;
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
            System.out.println("Bad birth date!");
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

    @Override
    public String showContactInformation() {
        return String.format("""
                Name: %s
                Surname: %s
                Birth date: %s
                Gender: %s
                Number: %s
                Time created: %s
                Time last edit: %s""",this.firstName ,this.lastName,
                this.dateOfBirth, this.gender,super.getPhoneNumber(),
                super.getTimeCreated(),super.getTimeLastEdit());
    }

    @Override
    public ContactDetails addNewContact(InputOutputData reader) {
            System.out.println("Enter the name of the person:");
            this.firstName = reader.readLine();
            System.out.println("Enter the surname of the person:");
            this.lastName = reader.readLine();
            System.out.println("Enter the birth date:");
            setDateOfBirth(reader.readLine());
            System.out.println("Enter the gender (M, F):");
            setGender(reader.readLine());
            System.out.println("Enter the number:");
            setPhoneNumber(reader.readLine());
        return this;
    }

    @Override
    public ContactDetails editUserInformation(InputOutputData reader) {
        System.out.println("Select a field (name, surname, number,gender,birth day):");
        switch (reader.readLine()) {
            case "name" -> {
                System.out.println("Enter name");
                this.firstName = reader.readLine();
            }
            case "surname" -> {
                System.out.println("Enter surname");
                this.lastName = reader.readLine();
            }
            case "number" -> {
                System.out.println("Enter number");
                setPhoneNumber(reader.readLine());
            }
            case "gender" -> {
                System.out.println("Enter gender");
                setGender(reader.readLine());
            }
            case "birth day" -> {
                System.out.println("Enter birthday");
                setDateOfBirth(reader.readLine());
            }
            default -> System.out.println("Invalid Option");
        }
        return this;
    }

    @Override
    public String showBasicInformation() {
        return String.format("%s %s",this.firstName,this.lastName);
    }
}
