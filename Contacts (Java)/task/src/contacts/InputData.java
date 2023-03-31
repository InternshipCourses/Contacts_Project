package contacts;

import java.io.Closeable;
import java.io.InputStream;
import java.util.function.Supplier;

public interface InputData<T> extends Supplier<T>, Closeable {

}

