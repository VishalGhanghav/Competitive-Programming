package SdeSheetHeaps.Learning;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class MinHeapImplementation {
    int capacity;
    int size;
    int[] items;

    public MinHeapImplementation(int capacity) {
        this.capacity = capacity;
        this.items = new int[capacity];
    }

    private int getLeftChildIndex(int currIndex) {
        return 2*currIndex + 1;
    }

    private int getRightChildIndex(int currIndex) {
        return 2*currIndex + 2;
    }

    private int getParentIndex(int currIndex) {
        return (currIndex-1)/2;
    }

    private int getLeftChild(int currIndex) {
        return items[getLeftChildIndex(currIndex)];
    }

    private int getRightChild(int currIndex) {
        return items[getRightChildIndex(currIndex)];
    }

    private int getParent(int currIndex) {
        return items[getParentIndex(currIndex)];
    }

    private boolean hasLeftChild(int currIndex) {
        //If available within curr array size.It has left child
        return getLeftChildIndex(currIndex) < size;
    }

    private boolean hasRightChild(int currIndex) {
        //If available within curr array size.It has left child
        return getRightChildIndex(currIndex) < size;
    }

    private boolean hasParent(int currIndex) {
        //If available within curr array size.It has left child
        return getParentIndex(currIndex) >= 0;
    }

    private void swap(int ind1, int ind2) {
        int temp = items[ind1];
        items[ind1] = items[ind2];
        items[ind2] = temp;
    }

    private void ensureExtraCapacity() {
        if(size == capacity) {
            items = Arrays.copyOf(items, capacity*2);
            capacity = capacity*2;
            System.out.println("Increased Capacity");
        }
    }

    public int peek() {
        if(size == 0) {
            throw new IllegalStateException();
        }
        return items[0];
    }

    public void add(int val) {
        ensureExtraCapacity();
        items[size] = val;
        size++;
        heapifyUp();
    }

    public int poll() {
        if(size == 0) throw  new IllegalStateException();
        int item = items[0];
        items[0] = items[size-1];
        size--;
        heapifyDown();
        return item;
    }

    private void heapifyUp() {
        int index = size-1;
        while(hasParent(index) && getParent(index)>items[index]) {
            swap(getParentIndex(index),index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        //If has left child then obviously lower level is there
        while(hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index) && getRightChild(index) < items[smallerChildIndex]) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if(items[index] < items[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
                index = smallerChildIndex;
            }
        }
    }

    public static void main(String[] args) {
        MinHeapImplementation minHeap = new MinHeapImplementation(3);
        minHeap.add(2);
        minHeap.add(3);
        minHeap.add(5);

        System.out.println("peek:"+minHeap.peek());
        System.out.println("poll:"+minHeap.poll());

        minHeap.add(6);
        minHeap.add(7);
        System.out.println("peek:"+minHeap.peek());

    }


}
