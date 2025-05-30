package manager;

import model.*;
import java.util.*;

public class TaskManager {

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    private int id = 1;

    private int makeID(){
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
        epic.addSubtaskID(id);
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
        for (int subID : epic.getSubtasksID()){
            subtasks.remove(subID);
        }
    }

    public void removeSubtask (int id){
        Subtask subtask = subtasks.remove(id);
        Epic epic = epics.get(subtask.getEpicID());
        epic.removeSubtask(id);
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
           epic.setStatus(Status.NEW);
       }
    }


    private void updateEpicsStatus(int epicID){
        Epic epic = epics.get(epicID);

        List<Integer> epicSubtasksID = epic.getSubtasksID();
        if(epicSubtasksID.isEmpty()){
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (int id : epicSubtasksID){
            if(getSubtask(id).getStatus() == Status.IN_PROGRESS){
                epic.setStatus(Status.IN_PROGRESS);
                return;
            }
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
        Epic oldEpic = epics.get(id);
        updatedEpic.setStatus(oldEpic.getStatus());
        for(int subtaskID : oldEpic.getSubtasksID()){
            updatedEpic.addSubtaskID(subtaskID);
        }
        epics.put(id, updatedEpic);
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
