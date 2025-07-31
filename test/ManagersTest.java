package test;

import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManagersTest {

    @Test
    public void shouldReturnInitialisedTasKManager() {
        TaskManager manager = Managers.getDefault();
        assertNotNull(manager, "Менеджер задач должен быть проинициализирован");
    }

    @Test
    public void shouldReturnInitialisedHistoryManager() {
        HistoryManager manager = Managers.getDefaultHistory();
        assertNotNull(manager, "Менеджер истории должен быть проинициализирован");
    }

}