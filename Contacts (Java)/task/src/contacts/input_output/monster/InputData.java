package contacts.input_output.monster;

import java.io.Closeable;

import java.util.function.Supplier;

public interface InputData<T> extends Supplier<T>, Closeable {

}

