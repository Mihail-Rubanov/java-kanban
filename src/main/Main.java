package main;

import manager.TaskManager;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Переезд", "Собрать вещи", TaskStatus.NEW);
        Task task2 = new Task("Уборка", "Выкинуть мусор", TaskStatus.NEW);
        Epic epic1 = new Epic("Стать легенодой в Java", "Сдать все проекты к жёсткому дэдлайну");
        SubTask subTask1 = new SubTask("Сдать ТЗ 4", "Сдать к 25му ноября", TaskStatus.NEW, 3);
        SubTask subTask2 = new SubTask("Сдать ТЗ 5", "Сдать к 25му ноября", TaskStatus.NEW, 3);
        Epic epic2 = new Epic("Создать игру", "Придумать уровни");
        SubTask subTask3 = new SubTask("Придумать уровень 1", "Продумать обучение", TaskStatus.NEW,
                6);

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createEpic(epic1);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createEpic(epic2);
        taskManager.createSubTask(subTask3);
        System.out.println(taskManager.getTasksList());
        System.out.println(taskManager.getEpicsList());
        System.out.println(taskManager.getSubTasksList());
        taskManager.updateTask(task1);
        taskManager.updateTask(task2);
        taskManager.updateSubTask(subTask1);
        taskManager.updateSubTask(subTask3);
        taskManager.updateEpic(epic1);
        taskManager.updateEpic(epic2);
        System.out.println(taskManager.getTasksList());
        System.out.println(taskManager.getEpicsList());
        System.out.println(taskManager.getSubTasksList());
        taskManager.removeTaskById(1);
        taskManager.removeEpicById(3);
        System.out.println(taskManager.getTasksList());
        System.out.println(taskManager.getEpicsList());
        System.out.println(taskManager.getSubTasksList());
    }
}