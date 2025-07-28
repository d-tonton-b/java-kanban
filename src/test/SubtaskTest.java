package test;

import model.Status;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    public void subtasksShouldBeEqualWithSameID() {
        Subtask subtask1 = new Subtask(1, "test subtask1","Testing subtask", Status.NEW, 2);
        Subtask subtask2 = new Subtask(1, "test subtask2","Testing subtask", Status.NEW, 2);
        assertEquals(subtask1, subtask2, "Задачи с одинаковым ID должны быть равны");
    }

    @Test
    public void subtasksCanNotBeItsOwnEpic() {
        Subtask subtask1 = new Subtask(1, "test subtask1","Testing subtask", Status.NEW, 2);
        subtask1.setEpicID(1);
        assertTrue(subtask1.getEpicID() != 1, "Подзадача не может быть собственным эпиком");
    }


}