package manager;

import model.*;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    Task makeTask(Task task);

    Epic makeEpic(Epic epic);

    Subtask makeSubtask(Subtask subtask);

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtask(int id);

    void removeTask(int id);

    void removeEpic(int id);

    void removeSubtask(int id);

    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    void clearAllTasks();

    void clearAllEpics();

    void clearAllSubtasks();

    void updateEpicsStatus(int epicID);

    void updateSubtask(Subtask updatedSubtask);

    void updateTask(Task updatedTask);

    void updateEpic(Epic updatedEpic);

    List<Subtask> getEpicsSubtasks(int epicID);

    List<Task> getHistory();

}
