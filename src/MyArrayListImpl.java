import java.util.Collection;
import java.util.Comparator;

public class MyArrayListImpl<E extends Comparable<E>> implements MyOwnList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData = {};
    private int size;
    private final int rate = 4;
    private int pointer;

    public MyArrayListImpl() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayListImpl(int initialCapacity) {
        this.elementData = new Object[initialCapacity];
    }

    @Override
    public void add(int index, E element) {
        if (size == elementData.length-1) {
            resize(elementData.length*2);
        }
        elementData[size++] = element;
        pointer++;
    }

    private void resize(int len) {
        Object[] newData = new Object[len];
        System.arraycopy(elementData, 0, newData, 0, size);
        elementData = newData;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
         Object[] a = c.toArray();
        int length = a.length;
        Object[] data = this.elementData;
        if (length < size - pointer) {
            resize(data.length + length);
        }
        System.arraycopy(a, 0, data, pointer, length);
        size += length;
        pointer+=length;
    }

    @Override
    public void clear() {
        this.elementData = EMPTY_ELEMENTDATA;
        this.size = 0;
        this.pointer = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index: out of bounds");
        }
        return (E) elementData[index];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index: out of bounds");
        }
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size] = null;
        if (elementData.length > DEFAULT_CAPACITY && size < elementData.length / rate) {
            resize(elementData.length/2);
        }
        size--;
        pointer--;
    }

    @Override
    public void remove(Object o) {
        int ind = -1;
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i].equals(o)) {
                ind = i;
                break;
            }
        }
        if (ind != -1) {
            remove(ind);
        }
        size--;
        pointer--;
    }

    @Override
    public void sort() {
        mergeSort(elementData, 0, this.pointer-1);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        mergeSort((E[]) elementData, 0, this.pointer-1);
    }

    private void mergeSort(Object[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(Object[] arr, int left, int mid, int right) {
        E[] leftArray  = (E[]) new Comparable[mid - left + 1];
        E[] rightArray = (E[]) new Comparable[right - mid];

        for (int i = 0; i < leftArray.length; ++i)
            leftArray[i] = (E) arr[left + i];

        for (int i = 0; i < rightArray.length; ++i)
            rightArray[i] = (E) arr[mid + 1 + i];

        int leftIndex = 0, rightIndex = 0;

        int currentIndex = left;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex] != null && rightArray[rightIndex] != null &&
                    leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0) {
                arr[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            }
            else {
                arr[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        while (leftIndex < leftArray.length) {
            arr[currentIndex++] = leftArray[leftIndex++];
        }

        while (rightIndex < rightArray.length) {
            arr[currentIndex++] = rightArray[rightIndex++];
        }
    }

}
