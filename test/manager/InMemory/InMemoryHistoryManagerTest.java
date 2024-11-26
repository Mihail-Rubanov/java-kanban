package manager.InMemory;

import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskStatus;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    @Test
    void addTaskInHistory() {
        Task task = new Task("taskname","taskdescription",TaskStatus.IN_PROGRESS);
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        historyManager.add(task);
        final ArrayList<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }
}