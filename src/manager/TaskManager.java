package manager;

import model.*;
import java.util.*;

public class TaskManager {

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    private int id = 1;

    public int makeID(){
        return id++;
    }

    public Task makeTask(Task task){
        int id = makeID();
        task.setID(id);
        tasks.put(id, task);
        return task;
    }

    public Epic makeEpic(Epic epic){
        int id = makeID();
        epic.setID(id);
        epics.put(id, epic);
        return epic;
    }

    public Subtask makeSubtask (Subtask subtask){
        int epicID = subtask.getEpicID();
        Epic epic = epics.get(epicID);
        int id = makeID();
        subtask.setID(id);
        subtasks.put(id, subtask);
        updateEpicsStatus(epicID);
        return subtask;
    }

    public Task getTask (int id){
        return tasks.get(id);
    }

    public Epic getEpic (int id){
        return epics.get(id);
    }

    public Subtask getSubtask (int id){
        return subtasks.get(id);
    }

    public void removeTask (int id){
        tasks.remove(id);
    }

    public void removeEpic (int id){
        Epic epic = epics.remove(id);
        for (int subID : getEpicsSubtasksID(id)){
            subtasks.remove(id);
        }
    }

    public void removeSubtask (int id){
        Subtask subtask = subtasks.remove(id);
        updateEpicsStatus(subtask.getEpicID());
    }

    public ArrayList<Task> getAllTasks(){
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks(){
        return new ArrayList<>(subtasks.values());
    }

    public void clearAllTasks(){
        tasks.clear();
    }

    public void clearAllEpics(){
        epics.clear();
        subtasks.clear();
    }

    public void clearAllSubtasks(){
       subtasks.clear();
       for(Epic epic : epics.values()){
           updateEpicsStatus(epic.getID());
       }
    }


    public void updateEpicsStatus(int epicID){
        Epic epic = epics.get(epicID);

        List<Integer> epicSubtasksID = getEpicsSubtasksID(epicID);
        if(epicSubtasksID.isEmpty()){
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (int id : epicSubtasksID){
            if(getSubtask(id).getStatus() != Status.DONE){
                allDone = false;
            }
            if(getSubtask(id).getStatus() != Status.NEW){
                allNew = false;
            }
        }

        if (allNew){
            epic.setStatus(Status.NEW);
        } else if (allDone){
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    public void updateSubtask (Subtask updatedSubtask){
        int id = updatedSubtask.getID();
        subtasks.put(id, updatedSubtask);
        updateEpicsStatus(updatedSubtask.getEpicID());
    }

    public void updateTask (Task updatedTask){
        int id = updatedTask.getID();
        tasks.put(id, updatedTask);
    }

    public void updateEpic (Epic updatedEpic){
        int id = updatedEpic.getID();

        epics.put(id, updatedEpic);
        updateEpicsStatus(id);
    }




    public List<Integer> getEpicsSubtasksID(int epicID){

        ArrayList<Integer> subtasksID = new ArrayList<>();
        for (Subtask subtask : subtasks.values()){
            if (subtask.getEpicID() == epicID){
                subtasksID.add(subtask.getID());
            }
        }
        return  subtasksID;
    }

    public List<Subtask> getEpicsSubtasks (int epicID){
        List<Subtask> epicsSubtasks = new ArrayList<>();
        for(Subtask sub : subtasks.values()){
            if(sub.getEpicID() == epicID){
                epicsSubtasks.add(sub);
            }
        }
        return epicsSubtasks;
    }

}
