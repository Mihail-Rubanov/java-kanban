package history;

import task.Task;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


public class InMemoryHistoryManager implements HistoryManager {
    private final HandMadeLinkedList<Task> historyList = new HandMadeLinkedList<>();
    private final Map<Integer, Node<Task>> historyNodesMap = new HashMap<>();
    public Node<Task> lastTask;
    public Node<Task> oldTail = null;

    public static class HandMadeLinkedList<E> {
        private Node<E> head;
        private Node<E> tail;
        private int size = 0;
    }

    private void linkLast(Task task) {
        if (historyList.tail != null) {
            oldTail = historyList.tail;
        }
        final Node<Task> newNode = new Node<>(task, oldTail, null);
        if (oldTail == null) {
            historyList.head = newNode;
            historyList.tail = newNode;
        } else {
            oldTail.next = newNode;
            historyList.tail = newNode;
        }
        lastTask = newNode;
        historyList.size++;
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            System.out.println("Такой задачи нет");
            return;
        }
        if (historyNodesMap.containsKey(task.getId())) {
            removeNode(historyNodesMap.get(task.getId()));
        }
            linkLast(task);
            historyNodesMap.put(task.getId(), lastTask);
    }

    @Override
    public void remove(int id) {
        if (historyNodesMap.get(id) != null) {
            removeNode(historyNodesMap.get(id));
        }
    }

    private void removeNode(Node<Task> target) {
        Node<Task> next = target.next;
        Node<Task> prev = target.prev;
        if (prev == null) {
            next.prev = null;
            historyList.head = next;
        } else if (next == null) {
            prev.next = null;
            historyList.tail = prev;
        } else {
            next.prev = prev;
            prev.next = next;
        }
        historyNodesMap.remove(target.getTask().getId());
        historyList.size--;
    }

    @Override
    public ArrayList<Task> getHistory() {
        ArrayList<Task> history = new ArrayList<>();
        Node<Task> currentNode = historyList.head;
        while (currentNode != null) {
            history.add(currentNode.getTask());
            currentNode = currentNode.next;
        }
        return history;
    }
}
