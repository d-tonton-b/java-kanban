import  java.util.*;

public class TaskManager {

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    private int id = 1;

    public int makeID(){
        return id++;
    }

    public Task makeTask(String name, String description, Status status){
        int id = makeID();
        Task task = new Task (id, name, description, status);
        tasks.put(id, task);
        return task;
    }

    public Epic makeEpic(String name, String description){
        int id = makeID();
        Epic epic = new Epic (id, name, description);
        epics.put(id, epic);
        return epic;
    }

    public Subtask makeSubtask (String name, String description, Status status, int epicID){
        Epic epic = epics.get(epicID);
        int id = makeID();
        Subtask subtask = new Subtask(id, name, description, status, epicID);
        subtasks.put(id, subtask);
        epic.addSubtask(subtask);
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
        for (Subtask subtask : epic.getSubtasks()){
            subtasks.remove(subtask.getID());
        }
    }

    public void removeSubtask (int id){
        Subtask subtask = subtasks.remove(id);
        Epic epic = epics.get(subtask.getEpicID());
        epic.removeSubtask(subtask);
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

}
