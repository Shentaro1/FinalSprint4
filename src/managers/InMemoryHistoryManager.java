package managers;

import tasks.AbstractTask;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<AbstractTask> tasks;
    private final int SIZE;

    public InMemoryHistoryManager(int size) {
        tasks = new ArrayList<>();
        tasks.ensureCapacity(size);
        this.SIZE = size;
    }

    public void add(AbstractTask task) {
        if (tasks.size() == SIZE)
            tasks.removeLast();
        tasks.addFirst(task);
    }

    public ArrayList<AbstractTask> getHistory() {
        return new ArrayList<>(tasks);
    }

    public int getSize() {
        return SIZE;
    }
}
