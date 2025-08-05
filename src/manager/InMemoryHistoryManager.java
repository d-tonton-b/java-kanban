package manager;

import model.Task;

import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {


    private final HistoryLinkedList historyList = new HistoryLinkedList();
    private final HashMap<Integer, HistoryLinkedList.Node> nodeMap = new HashMap<>();


    @Override
    public List<Task> getHistory() {

        return historyList.getTasks();
    }

    @Override
    public void remove(int id) {

        HistoryLinkedList.Node node = nodeMap.remove(id);

        if (node != null) {
            historyList.removeNode(node);
        }
    }

    @Override
    public void add(Task task) {

        if (task != null) {
            remove(task.getID());
            HistoryLinkedList.Node newNode = historyList.linkLast(task);
            nodeMap.put(task.getID(), newNode);
        }

    }

}
