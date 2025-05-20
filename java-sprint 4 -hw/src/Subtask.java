public class Subtask extends Task{
    private int epicID;

    public Subtask(int id, String name, String description, Status status, int epicID) {
        super(id, name, description, status);
        this.epicID = epicID;
    }


    public int getEpicID() {
        return epicID;
    }

    public void setEpicID(int epicID) {
        this.epicID = epicID;
    }

    @Override
    public String toString(){
        return "Subtask {" +
                "id = " + id +
                "Epic id = " + epicID +
                ", status = " + status +
                ", name = " + name +
                ", description = " + description +
                "}";
    }
}
