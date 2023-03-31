package contacts.contactDetail;

import contacts.InputOutputData;
import contacts.concreteContactClass.Organization;
import contacts.concreteContactClass.Person;

public class ContactFactory {
    public static ContactDetails startContactFactory(InputOutputData reader) {
        System.out.println("Enter the type (person, organization):");
        String op = reader.readLine();
        ContactDetails newContact = switch (op.trim()) {
            case "person" ->  new Person();
            case "organization" -> new Organization();
            default -> null;
        };
        return (newContact != null) ? newContact.addNewContact(reader) : null;
    }
}
