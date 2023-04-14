package contacts.contactDetail;

import contacts.concreteContactClass.Organization;
import contacts.concreteContactClass.Person;
import contacts.input_output.impl.DataInputOutput;

public class ContactFactory {
    public static ContactDetails startContactFactory(/*InputOutputData*/DataInputOutput reader) {
        System.out.println("Enter the type (person, organization):");
        String op = reader.readLine();
        ContactDetails newContact = switch (op.trim()) {
//            case "person" ->  new PersonDTO();
            case "person" ->  new Person();
            case "organization" -> new Organization();
            default -> null;
        };
        return (newContact != null) ? newContact.addNewContact(reader) : null;
    }
}
