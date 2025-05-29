package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private List<Integer> subtasksID = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
    }

    public void addSubtaskID(int id){
        subtasksID.add(id);
    }

    public List<Integer> getSubtasksID (){
        return subtasksID;
    }

    public void removeSubtask(int id){
        subtasksID.remove(Integer.valueOf(id));

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
