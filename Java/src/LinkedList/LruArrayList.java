package LinkedList;

import java.util.Scanner;

public class LruArrayList<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private int capacity;

    private T[] array;

    public int getDataLength() {
        return dataLength;
    }

    private int dataLength;

    public LruArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public LruArrayList(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.dataLength = 0;
    }


    public void addData(T data) {
        // check exist
        int pos = findDataPos(data);
        // 如果在，移到最前
        if (-1 != pos) {
            // 删除原数据 右移
            rightShiftData(pos);
            // 在链表头插入
            insertHead(data);
        } else {// 如果不在
            // 如果满了，删除最后的
            rightShiftData(dataLength);
            // 在第一个插入
            insertHead(data);
        }

    }

    public boolean isEmpty() {
        return 0 == dataLength;
    }

    public boolean isFull() {
        return array.length == dataLength;
    }

    private int findDataPos(T data) {
        if (null == data) {
            throw new IllegalArgumentException("Param cannot be null");
        }
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i < dataLength; i++) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    private void rightShiftData(int endIndex) {
        System.out.println("right shift " + endIndex);
        if (0 == endIndex) return;
        if (isFull() && endIndex == dataLength) endIndex--;
        for (int i = endIndex; i >= 1; i--) {
            array[i] = array[i - 1];
        }
        if (isFull()) {
            dataLength--;
        }
    }

    private void insertHead(T data) {
        array[0] = data;
        dataLength++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(capacity * 2 + 2);
        sb.append("{");
        for (T data : array) {
            sb.append(data).append(",");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        LruArrayList<Integer> lruArrayList = new LruArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Input -1 to quit");
        int input;
        while (true) {
            input = sc.nextInt();
            if (-1 == input) break;
            lruArrayList.addData(input);
            System.out.println("Total length is: " + lruArrayList.getDataLength() + "\n" + lruArrayList.toString());
        }
    }

}
