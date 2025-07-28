package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<Integer> subtasksID = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
    }

    public void addSubtaskID(int subtaskID) {
        if (subtaskID == id) {
            System.out.println("Эпик не может стать своей собственной подзадачей");
            return;
        } else {
            subtasksID.add(subtaskID);
        }
    }

    public List<Integer> getSubtasksID() {
        return subtasksID;
    }

    public void removeSubtask(int id) {
        subtasksID.remove(Integer.valueOf(id));

    }

    public void clearAllSubtasksID() {
        subtasksID.clear();

    }

    @Override
    public String toString() {
        return "Epic {" +
                "id = " + id +
                ", status = " + status +
                ", name = " + name +
                ", description = " + description +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
