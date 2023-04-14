package contacts.input_output.monster.impl;

import contacts.input_output.impl.DataInputOutput;
import contacts.input_output.monster.InputData;
import contacts.input_output.monster.InputDataImpl;
import contacts.input_output.monster.OutputData;
import contacts.input_output.monster.OutputDataImp;
import contacts.exceptions.InputOutputDataException;

import java.io.*;

public class InputOutputData implements Closeable, DataInputOutput {

    private final InputData<String> console;
    private final InputData<Object> object;
    private final OutputData<Object> outputObject;
    private final OutputData<String> consoleOutput;

    public InputOutputData(String savedFileLocation) {
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(savedFileLocation)));
        } catch (FileNotFoundException e) {
            throw new InputOutputDataException("file not found", e);
        } catch (IOException e) {
            throw new InputOutputDataException("Input-Output exception", e);
        }

        this.object = new InputDataImpl<>(
                () -> {
                    try {
                        return objectInputStream.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        throw new InputOutputDataException(e);
                    }
                },
                objectInputStream::close
        );
        final ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(savedFileLocation)));
        } catch (IOException e) {
            throw new InputOutputDataException(e);
        }

        this.outputObject = new OutputDataImp<>(item -> {
            try {
                objectOutputStream.writeObject(item);
            } catch (IOException e) {
                throw new InputOutputDataException("Input Output error ",e);
            }
        }, objectOutputStream::close);

        final BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        this.console = new InputDataImpl<>(
                () -> {
                    try {
                        return reader1.readLine();
                    } catch (IOException e) {
                        throw new InputOutputDataException(e);
                    }
                },
                () -> reader1.close()
        );

        this.consoleOutput = new OutputDataImp<String>(
                item -> {
                    System.out.println(item);
                },
                () -> {
                    return;
                }
        );
    }

    @Override
    public void close() throws IOException {
        console.close();
        object.close();
        outputObject.close();
        consoleOutput.close();
    }

    public String readLine() {
        return console.get();
    }

    public void printToConsole(String text) {
        consoleOutput.accept(text);
    }

    public void saveObjectToFile(Object object) {
        outputObject.accept(object);
    }

    public Object readObjectToFile() {
        return object.get();
    }


}
