package model;

public class Subtask extends Task {
    private int epicID;

    public Subtask(int id, String name, String description, Status status, int epicID) {
        super(id, name, description, status);
        this.epicID = epicID;
    }


    public int getEpicID() {
        return epicID;
    }

    public void setEpicID(int epicID) {
        if (epicID == id) {
            System.out.println("Подазадача не может быть собственным эпиком");
            return;
        } else {
            this.epicID = epicID;
        }
    }

    @Override
    public String toString() {
        return "Subtask {" +
                "id = " + id +
                ", Epic id = " + epicID +
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
