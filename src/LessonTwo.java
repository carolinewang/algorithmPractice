import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haylin2002 on 1/8/16.
 */
public class LessonTwo{
    public static void main(StringExcersice[] args) {
        System.out.println(fibonacci(30));
        int[] array = new int[]{1,2,3,4,5,6};
        int[] array2 = new int[]{1,3,4,6,8};
        System.out.println(binarySearch(array, 2));
        System.out.println(firstOccur(array2, 4));
        System.out.println((lastOccur(array2, 4)));
        System.out.println((closest(array, 2)));
        System.out.println(Arrays.toString(kClosest(array2, 3, 3)));

        int[][] matrix = { {1,2,3,3,4},{4,5,6,7,10},{12,14,14,17,19},{22,22,22,24,25}};
        System.out.println(Arrays.toString(searchInMatrix(matrix,17)));
        System.out.println(Arrays.toString(searchInMatrix(matrix,24)));
        System.out.println(Arrays.toString(searchInMatrix(matrix,4)));

        List<Integer> arrayList = new ArrayList<>();


    }
    public int aPowerB (int a, int b){
        //base case
        if(b == 0){
            return 1;
        }
//        else if (b == 1){
//            return a;
//        }
        //recursive rule, time complexity O(logN), space complexity O(logN) (there are log_2_b levels,
        // every level use 1 space to store the half variable)
        int half = aPowerB(a, b/2); //everytime call recursive function once, there are log_2_b times
        if (b % 2 == 0){
            return half * half;
        }else{
            return half * half * a;
        }
    }

    //recursion solution
    public static long fibonacci(int K) {
        // Write your solution here
        if (K <= 0){
            return 0;
        } else if (K == 1 || K == 2) {
            return 1;
        }
        else{
            long n1 = fibonacci (K - 1);
            long n2 = fibonacci (K - 2);
            return  n1 + n2;
        }
    }

    //Dynamic Programming solution
    public long fibonacciDP(int K) {
        // Write your solution here
        if (K <= 0){
            return 0;
        }
        long a = 1; //K-2
        long b = 1; //K-1
        for (int i = 3; i <= K; i++){
            long newFib = a + b;
            b = a;
            a = newFib;
        }
        return a;
    }

    public static int binarySearch(int[] array, int target) {
        // Write your solution here
        int left = 0;
        int right = array.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (array[mid] == target){
                return mid;
            }else if (array[mid] < target){
                left = mid + 1;
            }else
                right = mid -1;
        }
        return -1;
    }

    public static int firstOccur(int[] array, int target) {
        // Write your solution here
        if (array == null || array.length == 0){
            return -1;
        }
        int left = 0;
        int right = array.length -1;
        while (left < right - 1){              //stop when left neighbors right
            int mid = left + (right - left) / 2;
            //once we find the target, still need to check in the left half if there is an earlier occurrence
            if(array [mid] >= target){
                right = mid;
            }else
                left = mid;
        }
        if (array [left] == target){
            return left;
        }else if (array[right] == target){
            return right;
        }else
            return -1;
    }

    public static int lastOccur(int[] array, int target) {
        // Write your solution here
        if (array == null || array.length == 0){
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1){
            int mid = (left + right) / 2;
            if (array[mid] <= target){
                //because we are finding the last occurrence,so once we find the target,
                // still need to continue finding in the right half
                left = mid;
            }else
                right = mid;
        }
        if (array[right] == target) {
            return right;
        }else if (array[left] == target){
            return left;
        }else
            return -1;
    }

    public static int closest(int[] array, int target) {
        // Write your solution here
        //the same as finding the firstOccurrence.
        if (array == null || array.length == 0){
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1){
            int mid = (left + right) / 2;
            if (array[mid] >= target){
                right = mid;
            }else
                left = mid;
        }
        //check if the left or right element is closer 取绝对值
        if (Math.abs(array[left] - target) <= Math.abs(array[right] - target)){
            return left;
        }else
            return right;
    }

    public static int[] kClosest(int[] array, int target, int k) {
        // Write your solution here
        //the same as finding the closest element
        int[] result = new int[k];
        if (array.length == 0){
            return result;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1){
            int mid = (left + right) / 2;
            if(array[mid] >= target){
                right = mid;
            }else{
                left = mid;
            }
        }
        //check whether left or right element is closer, do it k times to find k elements
        for (int i = 0; i < k; i++){
            //left pointer moves to left when right is out of bound, or left is not out of bound,
            // and left is closer to target
            if(right > array.length - 1 || left >= 0
                    && Math.abs(array[left] - target) <= Math.abs(array[right] - target)){
                        result[i] = array[left];
                        left--;
                }else {
                        result[i] = array[right];
                        right++;
                }
            }
        return result;
    }

    public static int[] searchInMatrix(int[][] matrix, int target) {
        // Write your solution here.
        int row = matrix.length;
        int column = matrix[0].length;

        int left = 0;
        int right = (row * column) - 1;

        while (left <= right){
            //compute the row and column index of the mid
            int mid = left + (right - left) / 2;
            int midRowIndex = mid / column ;
            int midColumnIndex = mid % column;
            if (matrix[midRowIndex][midColumnIndex] == target){
                return new int[]{midRowIndex, midColumnIndex};
            }else if (matrix[midRowIndex][midColumnIndex] < target){
                left = mid + 1;
            }else
                right = mid - 1;
        }
        return new int[] {-1, -1};
    }
}
