package contacts;

import contacts.input_output.impl.ConsoleInputOutput;

public class Main {

    public static void main(String[] args) {
        new ContactApp(new ConsoleInputOutput()).start();
    }
}
