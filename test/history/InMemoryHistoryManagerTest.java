package history;

import manager.InMemoryTaskManager;
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

        HistoryManager historyManager = Managers.getDefaultHistory();

        Task task = new Task("taskname","taskdescription",TaskStatus.IN_PROGRESS);
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void historyBecomeUnlimited() {

        TaskManager taskManager = Managers.getDefault();

        for (int i = 1; i <= 20; i++) {
            Task task = new Task("name" + i, "des" + i, TaskStatus.NEW);
            taskManager.createTask(task);
            taskManager.getTaskById(task.getId());
        }

        assertEquals(20, taskManager.getHistory().size());
    }

    @Test
    void historyWithoutRepeats() {
        TaskManager taskManager = Managers.getDefault();

        for (int i = 1; i <= 10; i++) {
            Task task = new Task("name" + i, "des" + i, TaskStatus.NEW);
            taskManager.createTask(task);
            taskManager.getTaskById(task.getId());
        }

        for (int i = 1; i <= 5; i++) {
            taskManager.getTaskById(i);
            System.out.println("прсмотр " + i + " есть");
        }

        System.out.println(taskManager.getHistory());
        assertEquals(10, taskManager.getHistory().size());
    }
}