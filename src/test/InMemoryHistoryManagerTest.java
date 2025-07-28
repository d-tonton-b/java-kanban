package test;

import manager.HistoryManager;
import manager.Managers;
import model.Status;
import model.Task;
import org.junit.jupiter.api.Test;
import java.util.List;

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

    @Test
    void shouldNotDuplicateTasks() {
        Task task1 = new Task(1, "task1", "test task1", Status.NEW);

        manager.add(task1);
        manager.add(task1);
        manager.add(task1);

        List<Task> history = manager.getHistory();
        assertEquals(1, history.size(), "История не должна содержать дубликатов");
    }

    @Test
    void shouldRemoveTaskById() {
        Task task1 = new Task(1, "task1", "test task1", Status.NEW);
        Task task2 = new Task(2, "task2", "test task2", Status.NEW);

        manager.add(task1);
        manager.add(task2);

        manager.remove(1);

        List<Task> history = manager.getHistory();
        assertFalse(history.contains(task1), "Удалённая задача не должна остаться в истории");
        assertTrue(history.contains(task2), "Задача, не подлежащая удалению, должна остаться");
    }

    @Test
    void shouldReturnEmptyListWhenNoTasks() {
        List<Task> history = manager.getHistory();
        assertTrue(history.isEmpty(), "История должна быть пустой, если задачи не добавлены");
    }

    @Test
    void shouldReturnTasksInCorrectOrder() {
        Task task1 = new Task(1, "task1", "test1", Status.NEW);
        Task task2 = new Task(2, "task2", "test2", Status.NEW);
        Task task3 = new Task(3, "task3", "test3", Status.NEW);

        manager.add(task1);
        manager.add(task2);
        manager.add(task3);

        List<Task> history = manager.getHistory();

        assertEquals(List.of(task1, task2, task3), history, "Задачи должны быть в порядке добавления");
    }

    @Test
    void shouldUpdatePositionIfReAdded() {
        Task task1 = new Task(1, "task1", "test1", Status.NEW);
        Task task2 = new Task(2, "task2", "test2", Status.NEW);
        Task task3 = new Task(3, "task3", "test3", Status.NEW);

        manager.add(task1);
        manager.add(task2);
        manager.add(task3);
        manager.add(task1); // Перемещаем task1 в конец

        List<Task> history = manager.getHistory();

        assertEquals(List.of(task2, task3, task1), history, "Задача должна быть перемещена в конец");
    }
}