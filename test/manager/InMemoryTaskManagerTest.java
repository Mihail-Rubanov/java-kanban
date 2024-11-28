package manager;

import history.HistoryManager;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private final TaskManager taskManager = Managers.getDefault();

    @Test
    void createTaskTest() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", TaskStatus.NEW);
        taskManager.createTask(task);
        final int taskId = task.getId();

        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasksList();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void tasksEqualsBySameIdTest() {
        Task task1 = new Task("name1","des1",TaskStatus.NEW);
        task1.setId(1);
        Task task2 = new Task("name2","des2",TaskStatus.IN_PROGRESS);
        task2.setId(1);

        assertEquals(task1, task2, "Задачи с одинаковыми id одинаковы!");
    }



    @Test
    void epicsEqualsBySameIdTest() {
        Epic epic1 = new Epic("name1","des1");
        epic1.setId(1);
        Task epic2 = new Epic("name2","des2");
        epic2.setId(1);

        assertEquals(epic1, epic2, "Эпики с одинаковыми id одинаковы!");
    }

    @Test
    void subTasksEqualsBySameIdTest() {
        Epic epic1 = new Epic("name1","des1");
        epic1.setId(1);
        SubTask subTask1 = new SubTask("name1","des1",TaskStatus.NEW, 1);
        subTask1.setId(2);
        SubTask subTask2 = new SubTask("name2","des2",TaskStatus.IN_PROGRESS, 1);
        subTask2.setId(2);

        assertEquals(subTask1, subTask2, "Задачи с одинаковыми id одинаковы!");
    }

    @Test
    void taskEqualSameTaskInTaskManager() {
        Task task1 = new Task("name1","des1",TaskStatus.NEW);
        taskManager.createTask(task1); // id = 1

        assertEquals(task1, taskManager.getTaskById(task1.getId()), "Задачи одинаковы!");
    }
}