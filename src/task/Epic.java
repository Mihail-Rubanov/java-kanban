package task;

import java.util.ArrayList;
import java.util.Arrays;

public class Epic extends Task {
    private ArrayList <SubTask> linkedSubtasks;

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
        linkedSubtasks = new ArrayList<>();
    }

    public ArrayList<SubTask> getLinkedSubtasks() {
        return linkedSubtasks;
    }

    public void setSubTaskInLinkedSubtasks(SubTask subTask) {
        linkedSubtasks.add(subTask);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", subTasks4Epic ='" + Arrays.toString(new ArrayList[]{linkedSubtasks}) + '\'' +
                '}';
    }
}