package contacts.input_output;

public interface ObjectInputOutput {
    void saveObjectToFile(Object object);
    Object readObjectToFile();
}
