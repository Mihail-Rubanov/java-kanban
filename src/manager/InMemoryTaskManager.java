package manager;

import history.HistoryManager;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private int counter = 1;

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();

    public InMemoryTaskManager() {

    }

    @Override
    public void createTask(Task task) {
        task.setId(counter);
        tasks.put(counter, task);
        counter++;
        System.out.println("Задача добавлена!");
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(counter);
        epics.put(counter, epic);
        counter++;
        System.out.println("Эпик добавлен");
    }

    @Override
    public void createSubTask(SubTask subTask) {
        subTask.setId(counter);
        subTasks.put(counter, subTask);
        counter++;
        Epic epic = epics.get(subTask.getEpicId());
        epic.setSubTaskInLinkedSubtasks(subTask);
        updateEpicStatus(subTask.getEpicId());
        System.out.println("Подзадача добавлена!");
    }

    @Override
    public void updateTask(Task task) {
        if (task != null) {
            task.setName("Новое имя " + task.getId());
            task.setDescription("Новое описание " + task.getId());
            task.setStatus(task.getStatus());
            System.out.println("Задача обновлена");
        } else {
            System.out.println("Ошибка! Такой задачи нет!");
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epic != null) {
            epic.setName("Новое имя " + epic.getId());
            epic.setDescription("Новое описание " + epic.getId());
            System.out.println("Эпик обновлен");
        } else {
            System.out.println("Ошибка! Такого эпика нет!");
        }
    }

    private void updateEpicStatus(int id) {
        int inEpicNEWStatusCount = 0;
        Epic epic = epics.get(id);
        List<SubTask> linkedSubtasks = epic.getLinkedSubtasks();
        for (SubTask subTask : linkedSubtasks) {
            if (subTask.getStatus() == TaskStatus.NEW) {
                inEpicNEWStatusCount += 1;
            }
        }
        if (inEpicNEWStatusCount == 0) {
            epic.setStatus(TaskStatus.DONE);
        } else if (inEpicNEWStatusCount == linkedSubtasks.size()) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        if (subTask != null) {
            subTask.setName("Новое имя " + subTask.getId());
            subTask.setDescription("Новое описание " + subTask.getId());
            subTask.setStatus(subTask.getStatus());
            updateEpicStatus(subTask.getEpicId());
            System.out.println("Подзадача обновлена");
        } else {
            System.out.println("Ошибка! Такой подзадачи нет!");
        }
    }

    @Override
    public List<Task> getTasksList() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getEpicsList() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> getSubTasksList() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void removeAllEpics() {
        subTasks.clear();
        epics.clear();
    }

    @Override
    public void removeAllSubTasks() {
        for (SubTask subTask : subTasks.values()) {
            for (Epic epic : epics.values()) {
                List<SubTask> linkedSubtasks = epic.getLinkedSubtasks();
                if (linkedSubtasks.contains(subTask)) {
                    linkedSubtasks.remove(subTask);
                    updateEpicStatus(subTask.getEpicId());
                }
            }
        }
        subTasks.clear();
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public SubTask getSubTaskById(int id) {
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
        System.out.println("Задача удалена");
    }

    @Override
    public void removeEpicById(int id) {
        Epic epic = epics.remove(id);
        List<SubTask> linkedSubtasks = epic.getLinkedSubtasks();
        for (SubTask subTask : linkedSubtasks) {
            subTasks.remove(subTask.getId());
        }
        System.out.println("Эпик удален");
    }

    @Override
    public void removeSubTaskById(int id) {
        SubTask subTask = subTasks.remove(id);
        Epic epic = epics.get(subTask.getEpicId());
        List<SubTask> linkedSubtasks = epic.getLinkedSubtasks();
        linkedSubtasks.remove(id);
        updateEpicStatus(subTask.getEpicId());
        System.out.println("Подзадача удалена");
    }

    @Override
    public List<SubTask> getEpicsSubtasks(int id) {
        Epic epic = epics.get(id);
        return epic.getLinkedSubtasks();
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}