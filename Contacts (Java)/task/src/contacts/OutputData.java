package contacts;

import java.io.Closeable;

public interface OutputData<T> extends Closeable {

    void put(T obj);

}
