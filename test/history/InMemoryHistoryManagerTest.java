package history;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    @Test
    void addTaskInHistory() {
        Task task = new Task("taskname","taskdescription",TaskStatus.IN_PROGRESS);
        HistoryManager historyManager = Managers.getDefaultHistory();
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void historyLimitIsWorking() {
        final int HISTORY_LIMIT = 10;

        TaskManager taskManager = Managers.getDefault();

        for (int i = 1; i <= 11; i++) {
            Task task = new Task("name" + i, "des" + i, TaskStatus.NEW);
            taskManager.createTask(task);
            taskManager.getTaskById(task.getId());
        }

        assertEquals(10, taskManager.getHistory().size());
    }
}