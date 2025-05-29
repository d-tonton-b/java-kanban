package model;

public class Task {

    protected String name;
    protected String description;
    protected int id;
    protected Status status;

    public Task (int id, String name, String description, Status status){
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getID(){
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Task {" +
                "id = " + id +
                ", status = " + status +
                ", name = " + name +
                ", description = " + description +
                "}";
    }

}
