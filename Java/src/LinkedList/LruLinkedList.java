package LinkedList;

import java.util.Scanner;

/**
 * 实现Lru的单链表
 */
public class LruLinkedList<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private int capacity;

    private int length;

    // head永远不变 作为index开头 head的next开始插入新的node
    private Node<T> headNode;

    public LruLinkedList(int capacity) {
        this.capacity = capacity;
        this.headNode = new Node<>();
        this.length = 0;
    }

    public LruLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public int getLength() {
        return length;
    }

    public void addData(T data) {
        // 先查在不在
        Node<T> preNode = findPreNodeByData(data);
        // 如果在，移到最前
        if (null != preNode) {
            // 删除原数据
            deleteDataByPreNode(preNode);
            // 在链表头插入
            insertFromHead(data);
        } else { // 如果不在
            // 如果满了，删除最后的
            if (this.length >= this.capacity) {
                deleteLastNode();
            }
            // 在第一个插入
            insertFromHead(data);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(capacity * 2 + 2);
        sb.append("{");
        Node<T> startNode = headNode.getNext();
        while (null != startNode.getNext()) {
            sb.append(startNode.getData()).append(",");
            startNode = startNode.getNext();
        }
        sb.append(startNode.getData()).append("}");

        return sb.toString();
    }

    private void deleteDataByPreNode(Node<T> preNode) {
        Node<T> targetNode = preNode.getNext();
        if (null != targetNode.getNext()) {
            preNode.setNext(targetNode.getNext());
        } else {
            preNode.setNext(null);
        }
        targetNode.release();
        this.length--;
    }

    /**
     * 删除最后一个结点
     *
     * @return whether delete success
     */
    private boolean deleteLastNode() {
        // 如果length为0 返回
        if (0 == this.length) {
            return false;
        }
        Node<T> preLastNode = headNode;
        // 下一个的下一个不空，移到下一个
        while (null != preLastNode.getNext().getNext()) {
            preLastNode = preLastNode.getNext();
        }
        Node<T> lastNode = preLastNode.getNext();
        lastNode.release(); // 删除最后一个
        preLastNode.setNext(null); // 最后一个的前一个变成最后一个
        this.length--;
        return true;
    }

    /**
     * 从开头插入数据
     *
     * @param data
     */
    private void insertFromHead(T data) {
        Node<T> oriFirst = headNode.getNext();
        this.headNode.setNext(new Node<>(data, oriFirst));
        this.length++;
    }

    /**
     * 查找目标元素的上一个Node，方便指针处理
     *
     * @param data
     * @return
     */
    private Node<T> findPreNodeByData(T data) {
        Node<T> node = headNode;
        while (null != node.next) {
            if (data.equals(node.getNext().getData())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public class Node<T> {
        private T data;
        private Node<T> next;

        public Node() {
            this.data = null;
            this.next = null;
        }

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public void release() {
            this.data = null;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LruLinkedList<Integer> lruLinkedList = new LruLinkedList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Input -1 to quit");
        int input;
        while (true) {
            input = sc.nextInt();
            if (-1 == input) break;
            lruLinkedList.addData(input);
            System.out.println("Total length is: " + lruLinkedList.getLength() + "\n" + lruLinkedList.toString());
        }
    }

}
