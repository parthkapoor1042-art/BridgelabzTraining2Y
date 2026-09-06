import java.util.Arrays;

public class IterativeBinarySearch {

    // * 1. BEST CASE TIME COMPLEXITY: O(1)

    // * 2. WORST CASE TIME COMPLEXITY: O(log N)

    // * 3. AVERAGE CASE TIME COMPLEXITY: O(log N)

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sortedArr = {0, 2, 3, 4, 8, 61};

        System.out.println("Sorted Array: " + Arrays.toString(sortedArr));

        System.out.println("Searching for 4: Index " + binarySearch(sortedArr, 4));
    }
}