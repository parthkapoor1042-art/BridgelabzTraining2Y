public class LinearSearch {

    //Best: O(1)
    //Average: O(1)
    //Worst: O(1)

    static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {8, 61, 2, 3, 4, 0};
        int key = 3;

        int result = linearSearch(arr, key);

        if (result != -1)
            System.out.println("Element found at index: " + result);
        else
            System.out.println("Element not found");
    }
}