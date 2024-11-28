package history;

import manager.Managers;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
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

        HistoryManager historyManager = Managers.getDefaultHistory();

        // All tasks for test (All TaskTypes)
        Task task1 = new Task("name1","description1",TaskStatus.DONE);
        Epic epic2 = new Epic("name2","description2");
        SubTask subTask3 = new SubTask("name3","description3",TaskStatus.NEW, 2);
        Task task4 = new Task("name4","description4",TaskStatus.NEW);
        Task task5 = new Task("name5","description5",TaskStatus.DONE);
        Task task6 = new Task("name6","description6",TaskStatus.IN_PROGRESS);
        Epic epic7 = new Epic("name7","description7");
        SubTask subTask8 = new SubTask("name8","description8",TaskStatus.NEW, 7);
        Task task9 = new Task("name9","description9",TaskStatus.IN_PROGRESS);
        Task task10 = new Task("name10","description10",TaskStatus.IN_PROGRESS);
        Task task11 = new Task("name11","description11",TaskStatus.DONE);

        // Adding in history Manager
        historyManager.add(task1);
        historyManager.add(epic2);
        historyManager.add(subTask3);
        historyManager.add(task4);
        historyManager.add(task5);
        historyManager.add(task6);
        historyManager.add(epic7);
        historyManager.add(subTask8);
        historyManager.add(task9);
        historyManager.add(task10);
        historyManager.add(task11);

        List<Task> testList = new ArrayList<>(HISTORY_LIMIT);

        // Adding in testList
        testList.add(epic2); // adding from 2
        testList.add(subTask3);
        testList.add(task4);
        testList.add(task5);
        testList.add(task6);
        testList.add(epic7);
        testList.add(subTask8);
        testList.add(task9);
        testList.add(task10);
        testList.add(task11);

        assertEquals(testList, historyManager.getHistory());
    }
}