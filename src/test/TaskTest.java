package test;

import model.Status;
import model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TaskTest {

    @Test
    public void tasksShouldBeEqualWithSameID() {
        Task task1 = new Task(1, "test task1","Testing task", Status.NEW);
        Task task2 = new Task(1, "test task2","Testing task", Status.NEW);
        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны");
    }

}