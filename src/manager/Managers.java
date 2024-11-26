package manager;

import manager.InMemory.InMemoryHistoryManager;
import manager.InMemory.InMemoryTaskManager;

public class Managers {

    public static InMemoryTaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static InMemoryHistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
