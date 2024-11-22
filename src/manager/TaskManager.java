package manager;

import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

import java.util.HashMap;
import java.util.ArrayList;

public class TaskManager {
    int counter = 1;

    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public void createTask(Task task) {
        task.setId(counter);
        tasks.put(counter, task);
        counter++;
        System.out.println("Задача добавлена!");
    }

    public void createEpic(Epic epic) {
        epic.setId(counter);
        epics.put(counter, epic);
        counter++;
        System.out.println("Эпик добавлен");
    }

    public void createSubTask(SubTask subTask) {
        subTask.setId(counter);
        subTasks.put(counter, subTask);
        counter++;
        Epic epic = epics.get(subTask.getEpicId());
        epic.setSubTaskInLinkedSubtasks(subTask);
        updateEpicStatus(subTask.getEpicId());
        System.out.println("Подзадача добавлена!");
    }

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
        ArrayList<SubTask> linkedSubtasks = epic.getLinkedSubtasks();
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

    public ArrayList<Task> getTasksList() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpicsList() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<SubTask> getSubTasksList() {
        return new ArrayList<>(subTasks.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpics() {
        subTasks.clear();
        epics.clear();
    }

    public void removeAllSubTasks() {
        for (SubTask subTask : subTasks.values()) {
            for (Epic epic : epics.values()) {
                ArrayList<SubTask> linkedSubtasks = epic.getLinkedSubtasks();
                if (linkedSubtasks.contains(subTask)) {
                    linkedSubtasks.remove(subTask);
                    updateEpicStatus(subTask.getEpicId());
                }
            }
        }
        subTasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
        System.out.println("Задача удалена");
    }

    public void removeEpicById(int id) {
        Epic epic = epics.remove(id);
        ArrayList<SubTask> linkedSubtasks = epic.getLinkedSubtasks();
        for (SubTask subTask : linkedSubtasks) {
            subTasks.remove(subTask.getId());
        }
        System.out.println("Эпик удален");
    }

    public void removeSubTaskById(int id) {
        SubTask subTask = subTasks.remove(id);
        Epic epic = epics.get(subTask.getEpicId());
        ArrayList<SubTask> linkedSubtasks = epic.getLinkedSubtasks();
        linkedSubtasks.remove(id);
        updateEpicStatus(subTask.getEpicId());
        System.out.println("Подзадача удалена");
    }

    public ArrayList<SubTask> getEpicsSubtasks(int id) {
        Epic epic = epics.get(id);
        return epic.getLinkedSubtasks();
    }
}