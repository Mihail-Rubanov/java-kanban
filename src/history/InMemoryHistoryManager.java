package history;

import task.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static java.util.List.copyOf;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> historyList = new LinkedList<>();
    private final Map<Integer, Node<Task>> historyNodesMap = new HashMap<>();
    public Node<Task> lastTask;

    @Override
    public void add(Task task) {
        if (task == null) {
            System.out.println("Такой задачи нет");
            return;
        }
        if (historyNodesMap.containsKey(task.getId())) {
            Map<Integer, Node<Task>> IterableNodesMap = new HashMap<>(historyNodesMap);
            for (Integer i : IterableNodesMap.keySet()) {
                if (i == task.getId()) {
                    removeNode(historyNodesMap.get(i));
                    linkLast(task);
                    historyList.add(task);
                    historyNodesMap.put(i, lastTask);
                }
            }
        } else {
            linkLast(task);
            historyList.add(task);
            historyNodesMap.put(task.getId(), lastTask);
        }
    }

    private void linkLast(Task task) {
        Node<Task> oldNode = lastTask;
        Node<Task> newNode = new Node<>(lastTask, task, null);
        if (lastTask == null) {
            lastTask = newNode;
        } else {
            oldNode.next = lastTask;
            lastTask = newNode;
        }
    }

    @Override
    public void remove(int id) {
        if (historyNodesMap.get(id) != null) {
            removeNode(historyNodesMap.get(id));
        }
    }

    private void removeNode(Node<Task> task) {
        Node<Task> next = task.next;
        Node<Task> prev = task.prev;
        if (prev == null) {
            next = null;
            historyList.remove(task.getTask());
            historyNodesMap.remove(task.getTask().getId());
        } else if (next == null) {
            prev.next = null;
            historyList.remove(task.getTask());
            historyNodesMap.remove(task.getTask().getId());
        } else {
            next.prev = prev.next;
            prev.next = next.prev;
            historyList.remove(task.getTask());
            historyNodesMap.remove(task.getTask().getId());
        }
    }

    @Override
    public List<Task> getHistory() {
        return copyOf(historyList);
    }
}
