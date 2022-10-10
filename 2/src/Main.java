import java.util.Arrays;

public class Main {

    private static boolean search(int[] numbers, int x) {

        int left = 0;
        int right = numbers.length - 1;

        while (left <= right){
            int middle = (left + right)/2;
            int value = numbers[middle];

            if(x > value)
                right = middle - 1;
            else if (x < value)
                left = middle + 1;
            else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int[] numbers = new int[1000000];
        for (int i = 0; i < numbers.length ; i++) {
            numbers[i] = numbers.length -(2*i);
        }

        int x = 105056;
        System.out.println(search(numbers, x));
    }


}
