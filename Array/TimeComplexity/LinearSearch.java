import java.util.Scanner;

public class LinearSearch {
    static int linearSearch(int arr[],int target){
        for(int i=0;i< arr.length;i++){   // best case O(1)
            if(arr[i]==target){           // Average case O(n)
                return i;                 // Worst case O(n)
            }
        }
        return -1;
    }

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = {8, 61, 2, 3, 4, 0};

        System.out.println("Element to be searched");
        int target = sc.nextInt();

        int result = linearSearch(arr,target);

        if(result == -1){
            System.out.println("Not Found");
        }
        else {
            System.out.println("Element Found at index " + result);
        }
    }
}
