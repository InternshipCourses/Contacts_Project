package contacts.concreteContactClass;

import contacts.contactDetail.ContactDetails;
import contacts.input_output.impl.DataInputOutput;
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
    public ContactDetails addNewContact(DataInputOutput reader)  {
        reader.printToConsole("Enter the organization name:");
        this.organizationName = reader.readLine();
        reader.printToConsole("Enter the address:");
        this.address = reader.readLine();
        reader.printToConsole("Enter the number:");
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
    public ContactDetails editUserInformation(DataInputOutput reader) {
        reader.printToConsole("Select a field (name,address,number):");
        switch (reader.readLine()){
            case "name" -> {
                reader.printToConsole("Enter name");
                this.organizationName  = reader.readLine();
            }
            case "address" -> {
                reader.printToConsole("Enter address");
                this.address = reader.readLine();
            }
            case "number" -> {
                reader.printToConsole("Enter phone number");
                setPhoneNumber(reader.readLine());
            }
            default -> reader.printToConsole("invalid Option");
        }
        return this;
    }

    @Override
    public String showBasicInformation() {
        return String.format("%s",organizationName);
    }


}
