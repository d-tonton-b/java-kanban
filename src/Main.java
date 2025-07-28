
import manager.Managers;
import model.*;
import manager.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        //можно ставить любой айди, так как далее таск менеджер поменяет на нужный
        Task userTask1 = new Task(0, "Моя первая задача","Ну, я ее сделал", Status.NEW);
        Task userTask2 = new Task(0, "Моя вторя задача","Ну, я ее тоже сделал", Status.NEW);

        manager.makeTask(userTask1);
        manager.makeTask(userTask2);

        Epic userEpic1 = new Epic(0,"Мой первый эпик", "Сделать 2 подзачи");

        manager.makeEpic(userEpic1);

        Subtask userSubtask1 = new Subtask(0,"Моя первая подзача", "Добавить к первому эпику", Status.NEW, userEpic1.getID());
        Subtask userSubtask2 = new Subtask(0,"Моя вторая подзача", "Добавить к первому эпику", Status.NEW, userEpic1.getID());

        manager.makeSubtask(userSubtask1);
        manager.makeSubtask(userSubtask2);

        Epic userEpic2 = new Epic(0,"Мой второй эпик", "Сделать 1 подзачу");

        manager.makeEpic(userEpic2);

        Subtask userSubtask3 = new Subtask(0,"Моя третья подзача", "Добавить ко второму эпику", Status.NEW, userEpic2.getID());

        manager.makeSubtask(userSubtask3);

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println();

        userTask2.setStatus(Status.DONE);
        userSubtask2.setStatus(Status.IN_PROGRESS);
        manager.updateSubtask(userSubtask2);
        userSubtask3.setStatus(Status.DONE);
        manager.updateSubtask(userSubtask3);

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println();

        manager.removeEpic(userEpic2.getID());
        manager.removeSubtask(userSubtask1.getID());
        manager.removeTask(userTask1.getID());

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println();

        Epic epic1 = new Epic(userEpic1.getID(), "Обновленный эпик","Обновить первый эпик");
        manager.updateEpic(epic1);

        Subtask subtask1 = new Subtask(userSubtask2.getID(), "Обновленная подзадача", "Обновить подзадачу", Status.DONE, userSubtask2.getEpicID());
        manager.updateSubtask(subtask1);

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println();

       /* manager.clearAllEpics();

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());
        */

        manager.clearAllSubtasks();

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());

        System.out.println(manager.getTask(2));
        System.out.println(manager.getTask(2));
        System.out.println(manager.getTask(2));
        System.out.println(manager.getTask(2));
        System.out.println(manager.getTask(2));
        System.out.println(manager.getTask(2));
        System.out.println(manager.getTask(2));
        System.out.println(manager.getTask(2));
        System.out.println(manager.getEpic(3));
        System.out.println(manager.getEpic(3));
        System.out.println(manager.getEpic(3));
        System.out.println(manager.getHistory());

    }
}