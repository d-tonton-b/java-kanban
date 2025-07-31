package manager;

public class HistoryLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void add(T el) {
        Node<T> newNode = new Node<>(tail, el, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public boolean remove(T el) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(el)) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private void unlink(Node<T> node) {
        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

        size--;
    }

    public T get(int index) {
        checkIndex(index);
        Node<T> current = node(index);
        return current.data;
    }

    private Node<T> node(int index) {
        checkIndex(index);
        Node<T> current;
        if (index < (size / 2)) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public Node<T> getTail() {
        return tail;
    }

    public Node<T> getHead() {
        return head;
    }

    public void removeNode(Node<T> node) {
        if (node == null) return;

        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

        size--;
    }
}