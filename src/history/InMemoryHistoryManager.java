package history;

import task.Task;

import java.util.LinkedList;
import java.util.List;

import static java.util.List.copyOf;

public class InMemoryHistoryManager implements HistoryManager {
    private final int HISTORY_LIMIT = 10;

    private final List<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            System.out.println("Такой задачи нет");
            return;
        }
        if (history.size() == HISTORY_LIMIT) {
            history.removeFirst();
        }
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return copyOf(history);
    }
}
