package manager;

import manager.InMemory.InMemoryTaskManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;
import java.util.ArrayList;

class ManagersTest {

    @Test
    void managerDoGetDefaultTest() {
        TaskManager manager1 = Managers.getDefault();
        HistoryManager manager2 = Managers.getDefaultHistory();
        Task task1 = new Task("name1","des1", TaskStatus.DONE);
        manager1.createTask(task1);
        manager2.add(task1);
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(task1);

        assertEquals(task1, manager1.getTaskById(1));
        assertEquals(testList, manager2.getHistory());
    }

    @Test
    void taskManagerSaveAllTasksTypeAndCanGetThemById() {
        InMemoryTaskManager manager1 = Managers.getDefault();
        Task task1 = new Task("name1","des1", TaskStatus.DONE);
        Epic epic1 = new Epic("name2","des2");
        SubTask subTask1 = new SubTask("name3","des3", TaskStatus.IN_PROGRESS, 2);
        manager1.createTask(task1);
        manager1.createEpic(epic1);
        manager1.createSubTask(subTask1);

        assertEquals(1, manager1.getSubTasksList().size());
        assertEquals(1, manager1.getSubTasksList().size());
        assertEquals(1, manager1.getSubTasksList().size());

        assertEquals(task1, manager1.getTaskById(1));
        assertEquals(epic1, manager1.getEpicById(2));
        assertEquals(subTask1, manager1.getSubTaskById(3));
    }
}