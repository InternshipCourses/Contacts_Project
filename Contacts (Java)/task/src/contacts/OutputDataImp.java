package contacts;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OutputDataImp<T> implements OutputData<T> {

    private  Consumer<T> consumer;
    private Closeable closeable;
    public OutputDataImp(Consumer<T> consumer, Closeable closeable){
        this.consumer = consumer;
        this.closeable = closeable;
    }
    @Override
    public void put(T obj) {
        this.consumer.accept(obj);
    }

    @Override
    public void close() throws IOException {
        this.closeable.close();
    }
}
