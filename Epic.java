package model;

public class Epic extends Task{

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
    }

    @Override
    public String toString(){
        return "Epic {" +
                "id = " + id +
                ", status = " + status +
                ", name = " + name +
                ", description = " + description +
                "}";
    }
}
