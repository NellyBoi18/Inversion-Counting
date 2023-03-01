import java.util.Scanner;

public class InversionCounting {
    public static long mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];
        long count = 0;

        for (int x = 0; x < leftArr.length; x++) {
            leftArr[x] = arr[left + x];
        }

        for (int x = 0; x < rightArr.length; x++) {
            rightArr[x] = arr[mid + 1 + x];
        }

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

        while (x < leftArr.length) {
            arr[z++] = leftArr[x++];
        }

        while (y < rightArr.length) {
            arr[z++] = rightArr[y++];
        }

        return count;
    }

    public static long mergeSortAndCount(int[] arr, int left, int right) {
        long count = 0;

        if (left < right) {
            int mid = (left + right) / 2;
            count += mergeSortAndCount(arr, left, mid);
            count += mergeSortAndCount(arr, mid + 1, right);
            count += mergeAndCount(arr, left, mid, right);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numInstances = in.nextInt();
        long[] output = new long[numInstances];

        for (int x = 0; x < numInstances; x++) {
            int n = in.nextInt();
            int[] arr = new int[n];

            for (int y = 0; y < n; y++) {
                arr[y] = in.nextInt();
            }

            output[x] = (mergeSortAndCount(arr, 0, n - 1));
        }

        in.close();

        for (int x = 0; x < numInstances; x++) {
            System.out.println(output[x]);
        }
    }
}
