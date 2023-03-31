package contacts.concreteContactClass;

import contacts.InputOutputData;
import contacts.contactDetail.ContactDetails;

import java.io.BufferedReader;
import java.io.IOException;


public class Organization extends ContactDetails {
    private String organizationName;
    private  String address;

    public Organization() {
        super("");
        this.address = "";
        this.organizationName = "";
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String newOrganizationName) {
        this.organizationName = newOrganizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }


    @Override
    public ContactDetails addNewContact(InputOutputData reader)  {
        System.out.println("Enter the organization name:");
        this.organizationName = reader.readLine();
        System.out.println("Enter the address:");
        this.address = reader.readLine();
        System.out.println("Enter the number:");
        super.setPhoneNumber(reader.readLine());
         return this;
    }

    @Override
    public String showContactInformation() {
       return String.format("""
                Organization name: %s
                Address: %s
                Number: %s
                Time created: %s
                Time last edit: %s""",this.organizationName,
                this.address, super.getPhoneNumber(),
                super.getTimeCreated(),super.getTimeLastEdit()
       );
    }

    @Override
    public ContactDetails editUserInformation(InputOutputData reader) {
        System.out.println("Select a field (name,address,number):");
        switch (reader.readLine()){
            case "name" -> {
                System.out.println("Enter name");
                this.organizationName  = reader.readLine();
            }
            case "address" -> {
                System.out.println("Enter address");
                this.address = reader.readLine();
            }
            case "number" -> {
                System.out.println("Enter phone number");
                setPhoneNumber(reader.readLine());
            }
            default -> System.out.println("invalid Option");
        }
        return this;
    }

    @Override
    public String showBasicInformation() {
        return String.format("%s",organizationName);
    }


}
