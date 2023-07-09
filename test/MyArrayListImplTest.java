import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListImplTest {

    @Test
    public void testIsEmpty() throws Exception {
        MyOwnList<Integer> list = new MyArrayListImpl<>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testNotEmpty() throws Exception {
        MyOwnList<Integer> list = new MyArrayListImpl<>();
        list.add(0, 1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() throws Exception {
        MyOwnList<String> list = new MyArrayListImpl<>();
        list.add(0, "1");
        list.add(1, "2");
        list.add(2, "3");
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGetShouldReturnValue() throws Exception {
        MyOwnList<Integer> list = new MyArrayListImpl<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        Integer val = list.get(1);
        assertEquals(2, val);
    }

    @Test
    public void testGetShouldReturnException() throws Exception {
        MyOwnList<Integer> list = new MyArrayListImpl<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        assertThrows(IllegalArgumentException.class, () -> list.get(3));
    }


    @Test
    public void testAdd() throws Exception {
        MyOwnList<Integer> list = new MyArrayListImpl<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);

        assertEquals(1, list.get(0));
    }

    @Test
    public void testAdd2() throws Exception {
        MyOwnList<Integer> list = new MyArrayListImpl<>(5);
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3, 4);
        list.add(4, 5);
        list.add(5, 6);
        assertEquals(1, list.get(0));
        assertEquals(5, list.get(4));
        assertEquals(6, list.get(5));
    }

    @Test
    public void testAddAll() throws Exception {
        List<Integer> list = List.of(1, 2, 3, 4);
        MyOwnList<Integer> ownList = new MyArrayListImpl<>();
        ownList.addAll(list);

        assertEquals(1, ownList.get(0));
    }

    @Test
    public void testSort() throws Exception {
        MyOwnList<Integer> list = new MyArrayListImpl<>();
        List<Integer> arr = List.of(5, 4, 1, 7, 8, 2);
        list.addAll(arr);
        list.sort();
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(5, list.get(3));
        assertEquals(7, list.get(4));
        assertEquals(8, list.get(5));
    }
}