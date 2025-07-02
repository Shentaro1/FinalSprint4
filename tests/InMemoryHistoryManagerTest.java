import managers.InMemoryHistoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.AbstractTask;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager hm;

    @BeforeEach
    void createInMemoryHistoryManager() {
        hm = (InMemoryHistoryManager) Managers.getDefaultHistory();
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
    void testOverflow() {
        Task task = new Task("a", "b");
        for (int i = 0; i < hm.getSize() + 1; i++) {
            hm.add(task);
        }

        assertEquals(
                hm.getSize(),
                hm.getHistory().size(),
                "Размер списка не должен превышать ограничение"
        );
    }
}