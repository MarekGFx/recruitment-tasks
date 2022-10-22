public class BinarySearch {

    public static boolean search(int[] numbers, int x) {

        if(numbers.length == 0){
            return false;
        }
        else if (x > numbers[0] || x < numbers[numbers.length-1]){
            return false;
        }
        else {
            int left = 0;
            int right = numbers.length - 1;

            while (left <= right) {
                int middle = (left + right) / 2;
                int value = numbers[middle];

                if (x > value)
                    right = middle - 1;
                else if (x < value)
                    left = middle + 1;
                else
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] numbers = new int[1000000];
        for (int i = 0; i < numbers.length ; i++) {
            numbers[i] = 2*numbers.length - (i*2);
        }

        int x = 2;
        System.out.println(search(numbers, x));
    }
}
