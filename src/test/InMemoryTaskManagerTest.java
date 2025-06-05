package test;

import model.*;
import manager.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class InMemoryTaskManagerTest {

    static TaskManager manager = Managers.getDefault();

    @AfterEach
    public void clearAll(){
        manager.clearAllEpics();
        manager.clearAllTasks();
        manager.clearAllSubtasks();
    }

    @Test
    public void shouldCreateDifferentTaskTypes(){
        Task task1 = new Task(1, "task1", "test task1", Status.NEW);
        manager.makeTask(task1);

        Epic epic1 = new Epic(1, "epic1", "test epic1");
        manager.makeEpic(epic1);

        Subtask subtask1 = new Subtask(1, "subtask1", "test subtask1", Status.NEW, epic1.getID());
        manager.makeSubtask(subtask1);

        assertNotNull(manager.getTask(task1.getID()), "Задача не создана");
        assertNotNull(manager.getSubtask(subtask1.getID()), "Подзадача не создана");
        assertNotNull(manager.getEpic(epic1.getID()), "Эпик не создан");

    }

    @Test
    public void shouldBeNoConflictBetweenTasksWithTheSameID(){
        Task task1 = new Task(1, "task1", "test task1", Status.NEW);
        manager.makeTask(task1);

        Epic epic1 = new Epic(1, "epic1", "test epic1");

        assertEquals(manager.getTask(task1.getID()), task1, "задачи пересекаются");

    }

    @Test
    public void shouldBeTheSameInManager(){
        Task task1 = new Task(2, "task1", "test task1", Status.NEW);
        Task inManager = manager.makeTask(task1);

        assertEquals(inManager.getDescription(), task1.getDescription(), "Разное Описание");
        assertEquals(inManager.getName(), task1.getName(), "Разное Название");
        assertEquals(inManager.getStatus(), task1.getStatus(), "Разный статус");
        assertEquals(inManager.getID(), task1.getID(), "Разный АйДи");


    }

}