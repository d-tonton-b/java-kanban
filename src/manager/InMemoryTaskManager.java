package manager;

import model.*;
import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private HistoryManager historyManager = Managers.getDefaultHistory();
    private int id = 1;


    @Override
    public int makeID(){
        return id++;
    }

    @Override
    public Task makeTask(Task task){
        int id = makeID();
        task.setID(id);
        tasks.put(id, task);
        return task;
    }

    @Override
    public Epic makeEpic(Epic epic){
        int id = makeID();
        epic.setID(id);
        epics.put(id, epic);
        return epic;
    }

    @Override
    public Subtask makeSubtask(Subtask subtask){
        int epicID = subtask.getEpicID();
        Epic epic = epics.get(epicID);
        int id = makeID();
        subtask.setID(id);
        subtasks.put(id, subtask);
        epic.addSubtaskID(id);
        updateEpicsStatus(epicID);
        return subtask;
    }

    @Override
    public Task getTask(int id){
        if(tasks.get(id) != null){
            historyManager.add(tasks.get(id));
        }
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id){
        if(epics.get(id) != null){
            historyManager.add(epics.get(id));
        }
        return epics.get(id);
    }

    @Override
    public Subtask getSubtask(int id){
        if(subtasks.get(id) != null){
            historyManager.add(subtasks.get(id));
        }
        return subtasks.get(id);
    }

    @Override
    public void removeTask(int id){
        tasks.remove(id);
    }

    @Override
    public void removeEpic(int id){
        Epic epic = epics.remove(id);
        for (int subID : epic.getSubtasksID()){
            subtasks.remove(subID);
        }
    }

    @Override
    public void removeSubtask(int id){
        Subtask subtask = subtasks.remove(id);
        Epic epic = epics.get(subtask.getEpicID());
        epic.removeSubtask(id);
        updateEpicsStatus(subtask.getEpicID());
    }

    @Override
    public ArrayList<Task> getAllTasks(){
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks(){
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void clearAllTasks(){
        tasks.clear();
    }

    @Override
    public void clearAllEpics(){
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void clearAllSubtasks(){
       subtasks.clear();
       for(Epic epic : epics.values()){
           epic.setStatus(Status.NEW);
           epic.clearAllSubtasksID();
       }
    }


    @Override
    public void updateEpicsStatus(int epicID){
        Epic epic = epics.get(epicID);

        List<Integer> epicSubtasksID = epic.getSubtasksID();
        if(epicSubtasksID.isEmpty()){
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (int id : epicSubtasksID){
            if(subtasks.get(id).getStatus() == Status.IN_PROGRESS){
                epic.setStatus(Status.IN_PROGRESS);
                return;
            }
            if(subtasks.get(id).getStatus() != Status.DONE){
                allDone = false;
            }
            if(subtasks.get(id).getStatus() != Status.NEW){
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

    @Override
    public void updateSubtask(Subtask updatedSubtask){
        int id = updatedSubtask.getID();
        subtasks.put(id, updatedSubtask);
        updateEpicsStatus(updatedSubtask.getEpicID());
    }

    @Override
    public void updateTask(Task updatedTask){
        int id = updatedTask.getID();
        tasks.put(id, updatedTask);
    }

    @Override
    public void updateEpic(Epic updatedEpic){
        int id = updatedEpic.getID();
        Epic oldEpic = epics.get(id);
        updatedEpic.setStatus(oldEpic.getStatus());
        for(int subtaskID : oldEpic.getSubtasksID()){
            updatedEpic.addSubtaskID(subtaskID);
        }
        epics.put(id, updatedEpic);
    }

    @Override
    public List<Subtask> getEpicsSubtasks(int epicID){
        List<Subtask> epicsSubtasks = new ArrayList<>();
        for(Subtask sub : subtasks.values()){
            if(sub.getEpicID() == epicID){
                epicsSubtasks.add(sub);
            }
        }
        return epicsSubtasks;
    }

    @Override
    public List<Task> getHistory(){
        return historyManager.getHistory();
    }
}
