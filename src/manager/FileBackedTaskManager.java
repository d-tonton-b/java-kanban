package manager;

import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    @Override
    public void makeSubtask(Subtask subtask) {
        super.makeSubtask(subtask);
        save();
    }

    @Override
    public void makeTask(Task task) {
        super.makeTask(task);
        save();
    }

    @Override
    public void makeEpic(Epic epic) {
        super.makeEpic(epic);
        save();
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writer.write("id,type,name,status,description,epic\n");
            for (Task task : getAllTasks()) {
                writer.write(toString(task));
                writer.newLine();
            }
            for (Epic epic : getAllEpics()) {
                writer.write(toString(epic));
                writer.newLine();
            }
            for (Subtask subtask : getAllSubtasks()) {
                writer.write(toString(subtask));
                writer.newLine();
            }


        } catch (IOException e) {
            throw new ManagerException("Ошибка при сохранении данных в файл: " + file.getName(), e);
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        try {
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (int i = 1; i < lines.size(); i++) { // пропускаем заголовок
                Task task = fromString(lines.get(i));
                switch (task.getType()) {
                    case TASK:
                        manager.makeTask(task);
                        break;
                    case EPIC:
                        manager.makeEpic((Epic) task);
                        break;
                    case SUBTASK:
                        manager.makeSubtask((Subtask) task);
                        break;
                }
            }
        } catch (IOException e) {
            throw new ManagerException("Ошибка при загрузке данных из файла: " + file.getName(), e);
        }
        return manager;
    }

    private static String toString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getID()).append(",");
        sb.append(task.getType()).append(",");
        sb.append(task.getName()).append(",");
        sb.append(task.getStatus()).append(",");
        sb.append(task.getDescription()).append(",");
        sb.append(task instanceof Subtask ? ((Subtask) task).getEpicID() : "");
        return sb.toString();
    }

    private static Task fromString(String value) {
        String[] fields = value.split(",", -1);
        int id = Integer.parseInt(fields[0]);
        TaskType type = TaskType.valueOf(fields[1]);
        String name = fields[2];
        Status status = Status.valueOf(fields[3]);
        String description = fields[4];

        switch (type) {
            case TASK:
                return new Task(id, name, description, status);
            case EPIC:
                return new Epic(id, name, description);
            case SUBTASK:
                int epicId = Integer.parseInt(fields[5]);
                return new Subtask(id, name, description, status, epicId);
            default:
                throw new IllegalArgumentException("Неизвестный тип задачи: " + type);
        }
    }
}

