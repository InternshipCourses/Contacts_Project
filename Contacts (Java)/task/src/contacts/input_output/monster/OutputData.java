package contacts.input_output.monster;

import java.io.Closeable;
import java.util.function.Consumer;

public interface OutputData<T> extends Closeable, Consumer {

}
