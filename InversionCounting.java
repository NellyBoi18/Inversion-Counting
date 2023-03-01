import java.util.Scanner;

public class InversionCounting {
    // This method takes in an array and indices of the first, middle, and last elements of the array.
    // It merges the two subarrays and returns the number of inversions.
    public static long mergeAndCount(int[] arr, int left, int mid, int right) {
        // Create two subarrays from the given array
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];
        long count = 0;
        
        // Copy elements from the given array into the subarrays
        for (int x = 0; x < leftArr.length; x++) {
            leftArr[x] = arr[left + x];
        }

        for (int x = 0; x < rightArr.length; x++) {
            rightArr[x] = arr[mid + 1 + x];
        }

        // Merge the subarrays into the given array and count the number of inversions
        int x = 0;
        int y = 0;
        int z = left;
        while (x < leftArr.length && y < rightArr.length) {
            if (leftArr[x] <= rightArr[y]) {
                arr[z++] = leftArr[x++];
            } else {
                arr[z++] = rightArr[y++];
                count += mid - left + 1 - x;
            }
        }

        // Copy the remaining elements from the left subarray, if any
        while (x < leftArr.length) {
            arr[z++] = leftArr[x++];
        }

        // Copy the remaining elements from the right subarray, if any
        while (y < rightArr.length) {
            arr[z++] = rightArr[y++];
        }

        // Return the number of inversions
        return count;
    }

    // This method takes in an array and indices of the first and last elements of the array.
    // It recursively sorts the array and counts the number of inversions.
    public static long mergeSortAndCount(int[] arr, int left, int right) {
        long count = 0;

        if (left < right) {
            // Find the middle index of the array
            int mid = (left + right) / 2;
            // Recursively sort and count inversions in the left and right halves of the array
            count += mergeSortAndCount(arr, left, mid);
            count += mergeSortAndCount(arr, mid + 1, right);
            // Merge the two sorted halves and count inversions
            count += mergeAndCount(arr, left, mid, right);
        }

        // Return the total number of inversions
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numInstances = in.nextInt();
        long[] output = new long[numInstances];

        // For each instance, read in the number of elements, create an array, and read in the elements
        for (int x = 0; x < numInstances; x++) {
            int n = in.nextInt();
            int[] arr = new int[n];

            for (int y = 0; y < n; y++) {
                arr[y] = in.nextInt();
            }

            // Sort the array and count the number of inversions
            output[x] = (mergeSortAndCount(arr, 0, n - 1));
        }

        in.close();

        // Output the number of inversions for each instance
        for (int x = 0; x < numInstances; x++) {
            System.out.println(output[x]);
        }
    }
}
