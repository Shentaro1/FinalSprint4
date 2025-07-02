import managers.*;

public class Managers {
    public static HistoryManager getDefaultHistory() {return new InMemoryHistoryManager(10);
    }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    }
}
