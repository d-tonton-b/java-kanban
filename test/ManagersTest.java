package test;

import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManagersTest {

    @Test
    public void shouldReturnInitialisedTasKManager() {
        File file = new File("test.csv");
        TaskManager manager = Managers.getDefault(file);
        assertNotNull(manager, "Менеджер задач должен быть проинициализирован");
    }

    @Test
    public void shouldReturnInitialisedHistoryManager() {
        HistoryManager manager = Managers.getDefaultHistory();
        assertNotNull(manager, "Менеджер истории должен быть проинициализирован");
    }

}