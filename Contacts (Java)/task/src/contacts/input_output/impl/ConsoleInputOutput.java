package contacts.input_output.impl;

import java.io.*;

public class ConsoleInputOutput implements DataInputOutput {

    private final String savedLocation;
    public ConsoleInputOutput(){
        savedLocation = "";
    }
    public ConsoleInputOutput(String input){
        savedLocation = input;
    }
    @Override
    public String readLine() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printToConsole(String text) {
        System.out.println(text);
    }

    @Override
    public void saveObjectToFile(Object object) {
        if (savedLocation.equals("")){
            return;
        }
        System.out.println("saving here");
        try (ObjectOutputStream objectInputStream =
                     new ObjectOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(savedLocation)))){
            objectInputStream.writeObject(object);
            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object readObjectToFile() {
        if (savedLocation.equals("")){
            return null;
        }
        ObjectInputStream objectInputStream;
        try (var objInput= new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(savedLocation)))){
            objectInputStream = objInput;
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return objectInputStream = null;
        }

    }
}
