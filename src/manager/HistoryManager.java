package manager;

import java.util.ArrayList;
import model.*;

public interface HistoryManager {

    ArrayList<Task> getHistory();

    void add(Task task);
}
