package history;

import task.Task;

public class Node<E> {
    public E data;
    public Node<E> next;
    public Node<E> prev;

    public Node(Node<E> prev, E data, Node<E> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public Task getTask() {
        return (Task) data;
    }
}
