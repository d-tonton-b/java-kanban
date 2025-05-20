public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Epic epic1 = manager.makeEpic("Мой первый эпик", "Сделать 2 подзачи");
        Subtask subtask1 = manager.makeSubtask("Моя первая подзача", "Добавить к первому эпику", Status.NEW, epic1.getID());
        Subtask subtask2 = manager.makeSubtask("Моя вторая подзача", "Добавить к первому эпику", Status.NEW, epic1.getID());

        Epic epic2 = manager.makeEpic("Мой второй эпик", "Сделать 1 подзачу");
        Subtask subtask3 = manager.makeSubtask("Моя третья подзача", "Добавить ко второму эпику", Status.NEW, epic2.getID());

        Task task1 = manager.makeTask("Моя первая задача","Ну, я ее сделал", Status.NEW);

        Task task2 = manager.makeTask("Моя вторя задача","Ну, я ее тоже сделал", Status.NEW);

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());

        task2.setStatus(Status.DONE);
        subtask2.setStatus(Status.IN_PROGRESS);
        subtask3.setStatus(Status.DONE);

        epic2.updateStatus();
        epic1.updateStatus();

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());

        manager.removeEpic(4);
        manager.removeSubtask(2);
        manager.removeTask(6);

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());
    }
}