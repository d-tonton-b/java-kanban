package test;

import manager.FileBackedTaskManager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileBackedTaskManagerTest {

    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("tasks", ".csv");
    }

    @Test
    void saveAndLoadEmptyManager() {
        FileBackedTaskManager manager = new FileBackedTaskManager(tempFile);
        FileBackedTaskManager loaded = FileBackedTaskManager.loadFromFile(tempFile);

        assertTrue(loaded.getAllTasks().isEmpty());
        assertTrue(loaded.getAllEpics().isEmpty());
        assertTrue(loaded.getAllSubtasks().isEmpty());
    }

    @Test
    void saveAndLoadSingleTask() {
        FileBackedTaskManager manager = new FileBackedTaskManager(tempFile);

        Task task = new Task(1, "Task1", "desc", Status.NEW);
        manager.makeTask(task);

        FileBackedTaskManager loaded = FileBackedTaskManager.loadFromFile(tempFile);

        List<Task> tasks = loaded.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Task1", tasks.get(0).getName());
        assertEquals("desc", tasks.get(0).getDescription());
        assertEquals(Status.NEW, tasks.get(0).getStatus());
    }

    @Test
    void saveAndLoadEpicWithSubtasks() {
        FileBackedTaskManager manager = new FileBackedTaskManager(tempFile);

        Epic epic = new Epic(1, "Epic1", "Epic description");
        manager.makeEpic(epic);
        int epicId = epic.getID();

        Subtask sub1 = new Subtask(1, "Subtask1", "desc", Status.NEW, epicId);
        Subtask sub2 = new Subtask(1, "Subtask2", "desc", Status.DONE, epicId);

        manager.makeSubtask(sub1);
        manager.makeSubtask(sub2);

        FileBackedTaskManager loaded = FileBackedTaskManager.loadFromFile(tempFile);

        Epic loadedEpic = loaded.getEpic(epicId);
        List<Subtask> subtasks = loaded.getEpicsSubtasks(epicId);

        assertNotNull(loadedEpic);
        assertEquals(2, subtasks.size());
    }
}

