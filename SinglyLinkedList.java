import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here 
    public void swap() {
        if (size < 2) {
            return;
        }

        // put all the nodes into a list so we can index them
        List<Node<E>> nodes = new ArrayList<>(size);
        Node<E> current = head;
        while (current != null) {
            nodes.add(current);
            current = current.getNext();
        }
        int n = nodes.size();

        // sort the indexes by value, so order[0] is where the smallest is, order[1] the 2nd smallest etc
        List<Integer> order = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            order.add(i);
        }
        order.sort((a, b) -> nodes.get(a).getElement().compareTo(nodes.get(b).getElement()));

        // make a list with n empty slots so we can fill them in any order
        List<Node<E>> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(null);
        }

        // smallest goes where the biggest was, 2nd smallest where the 2nd biggest was, and so on
        for (int r = 0; r < n; r++) {
            result.set(order.get(n - 1 - r), nodes.get(order.get(r)));
        }

        // link everything back up in the new order
        for (int i = 0; i < n - 1; i++) {
            result.get(i).setNext(result.get(i + 1));
        }
        
        head = result.get(0);
        tail = result.get(n - 1);
        tail.setNext(null); // otherwise the old tail link could still point somewhere
    }
}
