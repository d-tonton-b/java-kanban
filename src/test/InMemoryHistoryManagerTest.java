package test;

import manager.HistoryManager;
import manager.Managers;
import model.Status;
import model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    HistoryManager manager = Managers.getDefaultHistory();

    @Test
    public  void shouldSaveTasksWithoutChanges(){
        Task task1 = new Task(1,"task1", "test task1", Status.NEW);

        manager.add(task1);

        Task task1History = manager.getHistory().get(0);

        assertEquals(task1History, task1, "Задачи должны быть равны");
    }
}