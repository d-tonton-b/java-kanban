package manager;

import java.util.ArrayList;
import model.*;

public class InMemoryHistoryManager implements  HistoryManager {

    private final int historyCapacity = 10;

    private ArrayList<Task> history = new ArrayList<>(historyCapacity);

    @Override
    public ArrayList<Task> getHistory(){

        return history;

    }

    @Override
    public void add(Task task){

        if(history.size() == historyCapacity){
            history.removeFirst();
        }
        history.addLast(task);

    }

}
