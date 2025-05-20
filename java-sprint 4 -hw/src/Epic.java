import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
    }

    private List<Subtask> subtasks = new ArrayList<>();

    public void addSubtask(Subtask subtask){
        subtasks.add(subtask);
        updateStatus();
    }

    public void removeSubtask(Subtask subtask){
        subtasks.remove(subtask);
        updateStatus();
    }

    public void clearSubtasks(){
        subtasks.clear();
        updateStatus();
    }

    public List<Subtask> getSubtasks (){
        return subtasks;
    }

    public void updateStatus(){

        if(subtasks.isEmpty()){
            this.status = Status.NEW;
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (Subtask subtask : subtasks){
            if(subtask.getStatus() != Status.DONE){
                allDone = false;
            }
            if(subtask.getStatus() != Status.NEW){
                allNew = false;
            }
        }

        if (allNew){
            this.status = Status.NEW;
        } else if (allDone){
            this.status = Status.DONE;
        } else {
            this.status = Status.IN_PROGRESS;
        }
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
