package org.demo.holder;

public class GenericHolder<T> {

    private T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":\n" + value;
    }
}
