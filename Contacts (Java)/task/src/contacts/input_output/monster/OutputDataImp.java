package contacts.input_output.monster;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;

public class OutputDataImp<T> implements OutputData<T> {

    private  Consumer<T> consumer;
    private Closeable closeable;
    public OutputDataImp(Consumer<T> consumer, Closeable closeable){
        this.consumer = consumer;
        this.closeable = closeable;
    }
    /*@Override
    public void put(T obj) {
        this.consumer.accept(obj);
    }*/

    @Override
    public void accept(Object o) {
        this.consumer.accept((T) o);
    }

    @Override
    public void close() throws IOException {
        this.closeable.close();
    }
}
