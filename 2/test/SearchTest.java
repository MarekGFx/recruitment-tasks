import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchTest {

    @Test
    public void searchWhenArrayIsEmptyTest(){
        int[] numbers = new int[0];
        int x = 10;
        Assertions.assertFalse(BinarySearch.search(numbers,x));
    }

    @Test
    public void searchWhenXIsGreaterTest(){
        int[] numbers = new int[1000];
        for (int i = 0; i < numbers.length ; i++) {
            numbers[i] = numbers.length - i;
        }
        int x = 1001;

        Assertions.assertFalse(BinarySearch.search(numbers,x));
    }

    @Test
    public void searchWhenXIsLowerTest(){
        int[] numbers = new int[1000];
        for (int i = 0; i < numbers.length ; i++) {
            numbers[i] = numbers.length - i;
        }
        int x = 0;

        Assertions.assertFalse(BinarySearch.search(numbers,x));
    }

    @Test
    public void searchWhenXOccursTest(){
        int[] numbers = new int[1000];
        for (int i = 0; i < numbers.length ; i++) {
            numbers[i] = 2 * numbers.length - (i*2);
        }
        int x = 1864;
        int x1 = 2;

        Assertions.assertTrue(BinarySearch.search(numbers,x));
        Assertions.assertTrue(BinarySearch.search(numbers,x1));
    }

    @Test
    public void searchWhenXNotOccursTest(){
        int[] numbers = new int[1000];
        for (int i = 0; i < numbers.length ; i++) {
            numbers[i] = 2 * numbers.length - (i*2);
        }
        int x = 1865;
        int x1 = 3;

        Assertions.assertFalse(BinarySearch.search(numbers,x));
        Assertions.assertFalse(BinarySearch.search(numbers,x1));
    }


}