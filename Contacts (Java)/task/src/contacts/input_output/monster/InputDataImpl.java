package contacts.input_output.monster;

import java.io.*;
import java.util.function.Supplier;

public class InputDataImpl<T> implements InputData<T> {

    private Supplier<T> reader;
    private Closeable closeable;

    public InputDataImpl(Supplier<T> reader, Closeable closeable) {
        this.reader = reader;
        this.closeable = closeable;
    }

    @Override
    public void close() throws IOException {
        closeable.close();
    }

    @Override
    public T get() {
            return reader.get();
    }

}
