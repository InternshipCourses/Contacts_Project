package contacts;

import java.io.*;

public class InputOutputData implements Closeable {

    public class InputOutputDataException extends RuntimeException {
        public InputOutputDataException(Throwable cause) {
            super(cause);
        }
    }

    private final InputData<String> console;
    private final InputData<Object> object;
    private final OutputData<Object> outputObject;
    private final OutputData<String> consoleOutput;



    public InputOutputData(String savedFileLocation){

        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(savedFileLocation)));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        final BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        this.object  = new InputDataImpl<>(

                () -> {
                    try {
                        return objectInputStream.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        throw new InputOutputDataException(e);
                    }
                },
                () -> {
                    objectInputStream.close();
                }

        );

        this.console =  new InputDataImpl<String>(
                () -> {
                    try {
                        return reader1.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    reader1.close();
                }
        );
        final  ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(savedFileLocation)));
        } catch (IOException e) {
            throw new InputOutputDataException (e);
        }

        this.outputObject = new OutputDataImp<Object>(item -> {
            try {
                objectOutputStream.writeObject(item);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, () -> objectOutputStream.close() );

        this.consoleOutput = new OutputDataImp<String>(
                item -> {
                    System.out.println(item);
                },
                ()->{
                    return;
                }
        );
    }

    @Override
    public void close() throws IOException {
        console.close();
        object.close();
    }
    public String readLine(){
        return console.get();
    }

    public void printToConsole(String text){
        consoleOutput.put(text);
    }
    public void saveObjectToFile(Object object){
        outputObject.put(object);
    }

    public Object readObjectToFile(){
        return object.get();
    }


}
