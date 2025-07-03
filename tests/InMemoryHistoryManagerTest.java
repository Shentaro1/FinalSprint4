import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.AbstractTask;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager hm;
    private InMemoryTaskManager tm;

    @BeforeEach
    void createInMemoryHistoryManager() {
        hm = (InMemoryHistoryManager) Managers.getDefaultHistory();
        tm = (InMemoryTaskManager) Managers.getDefault();
    }

    @Test
    void testAdd() {
        Task task = new Task("a", "b");
        hm.add(task);
        ArrayList<AbstractTask> list = hm.getHistory();

        assertEquals(1, list.size(), "Неверное кол-во задач");
        assertEquals(task, list.getLast(), "Задачи не совпадают");
    }

    @Test
    void testGetHistory() {
        assertNotNull(hm.getHistory(), "getHistory ничего не возвращает");
    }

    @Test
    void removeAndAddToEnd() {
        Task task1 = new Task("a", "b");
        tm.createTask(task1);
        tm.getTaskByID()

        hm.add();


        assertEquals(2, hm.getHistory().size(), "Размер не совпадает");
    }


}