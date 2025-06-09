package manager;

import java.util.ArrayList;
import model.*;

public class InMemoryHistoryManager implements  HistoryManager {

    private static final int HISTORY_CAPACITY = 10;;

    private ArrayList<Task> history = new ArrayList<>(HISTORY_CAPACITY);

    @Override
    public ArrayList<Task> getHistory(){

        return history;

    }

    @Override
    public void add(Task task){

        if(task != null) {
            if (history.size() == HISTORY_CAPACITY) {
                history.removeFirst();
            }
            history.addLast(task);
        }

    }

}
