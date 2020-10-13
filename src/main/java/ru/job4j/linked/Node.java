package ru.job4j.linked;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Node<T> {
    private final Node next;
    private final T value;

    public Node(final Node next, final T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() throws CloneNotSupportedException {
        return (Node) next.clone();
    }

    public T getValue() {
        T cloned = null;
        try {
            cloned = (T) value.getClass().getMethod("clone").invoke(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloned;
    }
}
