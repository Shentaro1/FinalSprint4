package managers;

import tasks.AbstractTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryHistoryManager<T> implements HistoryManager {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private Map<Integer, Node<T>> history = new HashMap<>();

    public static class Node<T> {
        public AbstractTask data;
        Node<T> next;
        Node<T> prev;

        public Node(AbstractTask data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public void linkLast(AbstractTask abstractTask) {
        if (history.containsKey(abstractTask.getId())) {
            removeAndAddToEnd(abstractTask.getId());
            return;
        }

        Node<T> newNode = new Node<>(abstractTask);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        history.put(abstractTask.getId(), newNode);
    }

    public void add(AbstractTask abstractTask) {
        linkLast(abstractTask);
    }

    private void removeAndAddToEnd(int id) {
        Node<T> nodeToRemove = history.get(id);

        if (nodeToRemove == null || head == null) {
            return;
        }

        if (nodeToRemove == tail) {
            return;
        }


        if (nodeToRemove == head) {
            head = head.next;
            head.prev = null;
        } else {
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
        }

        nodeToRemove.prev = tail;
        nodeToRemove.next = null;
        tail.next = nodeToRemove;
        tail = nodeToRemove;
    }

    public void remove(int id) {
        removeAndAddToEnd(id);
    }

    public ArrayList<AbstractTask> getHistory() {
        ArrayList<AbstractTask> historyList = new ArrayList<>(history.size());
        Node<T> current = head;
        while (current != null) {
            historyList.add(current.data.copy());
            current = current.next;
        }
        return historyList;
    }
}
