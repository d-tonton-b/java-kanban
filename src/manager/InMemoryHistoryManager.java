package manager;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {


    private final HistoryLinkedList<Task> historyList = new HistoryLinkedList<>();
    private final HashMap<Integer, Node<Task>> nodeMap = new HashMap<>();


    @Override
    public List<Task> getHistory() {

        List<Task> tasks = new ArrayList<>();
        Node<Task> current = historyList.getHead();
        while (current != null) {
            tasks.add(current.data);
            current = current.next;
        }
        return tasks;
    }

    @Override
    public void remove(int id) {

        Node<Task> node = nodeMap.remove(id);

        if (node != null) {
            historyList.removeNode(node);
        }
    }

    @Override
    public void add(Task task) {

        if (task != null) {
            remove(task.getID());
            historyList.add(task);
            nodeMap.put(task.getID(), historyList.getTail());
        }

    }

}
