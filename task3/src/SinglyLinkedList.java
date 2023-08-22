import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/*
 *Реализовано на примере LinkedList
*/

public class SinglyLinkedList<E> extends AbstractSequentialList<E> {

    transient int size = 0;
    transient Node<E> head;

    public SinglyLinkedList() {
    }



    @Override
    public int size() {
        return size;
    }

    public void reverse() {
        if (size <= 1) {  //Проверяем, что размер списка больше 1, иначе нет смысла менять местами эл-ты
            return; //Выходим из метода
        }

        Node<E> prev = null;
        Node<E> current = head; //Начинаем с первого элемента
        Node<E> next;

        while (current != null) {
            next = current.next; //Сохраняем ссылку на след. узел
            current.next = prev; // "Разворачиваем список"
                                //Меняем ссылку текущего узла на предыдущий
            prev = current;
            current = next; //переход к след узлу
        }
        head = prev; // новая "голова" списка
    }

    public void print() {
        Node<E> current = head;
        while (current != null) {
            System.out.println(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }
    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == 0) {
            linkFirst(element);
        } else if (index == size) {
            linkLast(element);
        }
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(index);
    }

    private void linkFirst(E e) {
        head = new Node<>(e, head);
        size++;
    }

    private void linkLast(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    private E unlink(int index) {
        if (index == 0) {
            E element = head.item;
            head = head.next;
            size--;
            return element;
        } else {
            Node<E> pred = node(index - 1);
            Node<E> current = pred.next;
            E element = current.item;
            pred.next = current.next;
            size--;
            return element;
        }
    }

    private Node<E> node(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;

        ListItr(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            this.next = (index == size) ? null : node(index);
            this.nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException("SinglyLinkedList does not support previous()");
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("SinglyLinkedList does not support remove()");
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("SinglyLinkedList does not support set()");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("SinglyLinkedList does not support add()");
        }
    }
}
