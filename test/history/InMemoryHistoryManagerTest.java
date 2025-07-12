package history;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    @Test
    void addTaskInHistory() {

        TaskManager taskManager = Managers.getDefault();

        Task task = new Task("taskname","taskdescription",TaskStatus.IN_PROGRESS);
        taskManager.createTask(task);
        taskManager.getTaskById(task.getId());

        assertNotNull(taskManager.getHistory(), "История не пустая.");
        assertEquals(1, taskManager.getHistory().size(), "История не пустая.");
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
        } // Порядок в истории задач: 1 2 3 4 5 6 7 8 9 10

        for (int i = 1; i <= 5; i++) {
            taskManager.getTaskById(i);
            System.out.println("прсмотр " + i + " есть");
        } // Порядок в истории задач: 6 7 8 9 10 1 2 3 4 5

        for (int i = 4; i >= 2; i--) {
            taskManager.getTaskById(i);
            System.out.println("прсмотр " + i + " есть");
        } // Порядок в истории задач: 6 7 8 9 10 1 5 4 3 2

        taskManager.getTaskById(8); // Порядок в истории задач: 6 7 9 10 1 5 4 3 2 8

        System.out.println(taskManager.getHistory());
        assertEquals(10, taskManager.getHistory().size());
    }
}