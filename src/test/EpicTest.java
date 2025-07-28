package test;

import model.Status;
import model.Epic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    public void epicsShouldBeEqualWithSameID() {
        Epic epic1 = new Epic(1, "test epic","Testing epic");
        Epic epic2 = new Epic(1, "test epic","Testing epic");
        assertEquals(epic1, epic2, "Задачи с одинаковым ID должны быть равны");
    }

    @Test
    public void subtasksCanNotBeItsOwnEpic() {
        Epic epic1 = new Epic(1, "test subtask1","Testing subtask");
        epic1.addSubtaskID(1);
        assertTrue(epic1.getSubtasksID().isEmpty(), "Подзадача не может быть собственным эпиком");
    }

}