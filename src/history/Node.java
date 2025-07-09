package history;

import task.Task;

public class Node<E> {
    public E data;
    public Node<E> prev;
    public Node<E> next;

    public Node(E data, Node<E> prev, Node<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public Task getTask() {
        return (Task) data;
    }
}
