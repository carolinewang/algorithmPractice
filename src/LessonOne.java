import java.util.Arrays;

/**
 * Created by haylin2002 on 1/4/16.
 */
public class LessonOne {
    public static void main(StringExcersice[] args) {
        int[] array = {7,4,1,-1,4,5};
        int[] array2 = {4,2,3,6,5,1};
        int[] array4 = {3,5,1,2,4,8};
        int[] array3 = {1, 0, 1, -1, 0,0,1,1,-1};
        int[] array5 = {1, 0, 3, 0, 1};
        int[] array6 = {0,2,2};
        System.out.print(Arrays.toString(selectionSort(array)));
        System.out.print(Arrays.toString(mergeSort(array4)));
        System.out.print(Arrays.toString(quickSort(array2)));
        System.out.print(Arrays.toString(rainbowSort(array3)));
        System.out.print(Arrays.toString(moveZero(array5)));
        System.out.print(Arrays.toString(moveZero(array6)));
    }

    public static int[] selectionSort(int[] array) {
        // Write your solution here.
        int global;
        int temp;

        for (int i = 0; i < array.length - 1; i++) {
            global = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[global]) {
                    global = j;
                }
            }
            temp = array[i];
            array[i] = array[global];
            array[global] = temp;
        }

        return array;
    }

        public static int[] mergeSort(int[] array) {
            // Write your solution here.
            if(array == null){
                return array;
            }
            int tempArr[] = new int[array.length];
            mergeSort(array, tempArr, 0, array.length - 1);
            return array;
        }

        private static void mergeSort(int[] array, int[] tempArr, int left, int right) {
            if (left < right) {
                int mid = (left + right) / 2;
                mergeSort(array, tempArr,left, mid);
                mergeSort(array, tempArr,mid+1, right);
                merge(array, tempArr, left, mid+1, right);
            }


        }

    //自己写的版本
        private static void merge(int[] array, int[] tempArr, int left, int mid, int right) {
            int rightIndex = mid + 1; //这里错了，因为给的Param mid已经是center +1,所以它实际上是右半个array的起始
            int tempPos = left;
            int numElements = right - left + 1;
            while (left <= mid && rightIndex <= right) { //应该是left < mid，而不是<=,因为mid实际上并不是mid，这个定义方式比较confusing
                if (array[left] < array[rightIndex]) {
                    tempArr[tempPos++] = array[left++];
                } else {
                    tempArr[tempPos++] = array[rightIndex++];
                }
            }
            while (left <= mid) {
                tempArr[tempPos++] = array[left++];
            }

            while (rightIndex <= right) {
                tempArr[tempPos++] = array[rightIndex++];
            }
            for (int i = 0; i < numElements; i++,right--) {
                array[right] = tempArr[right];
            }
        }

    //老师修改版
    private static void merge2(int[] array, int[] tempArr, int left, int mid, int right) {
        int rightIndex = mid;
        int tempPos = left;
        int numElements = right - left + 1;
        while (left < mid && rightIndex <= right) {
            if (array[left] < array[rightIndex]) {
                tempArr[tempPos++] = array[left++];
            } else {
                tempArr[tempPos++] = array[rightIndex++];
            }
        }
        while (left < mid) {
            tempArr[tempPos++] = array[left++];
        }

        while (rightIndex <= right) {
            tempArr[tempPos++] = array[rightIndex++];
        }
        for (int i = 0; i < numElements; i++,right--) {
            array[right] = tempArr[right];
        }
    }


    //weiss标准答案
    private static void merge3( int [ ] a, int [ ] tmpArray, int leftPos, int rightPos, int rightEnd )
    {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a[leftPos] < a[rightPos])
                tmpArray[ tmpPos++ ] = a[ leftPos++ ];
            else
                tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        while( leftPos <= leftEnd )    // Copy rest of first half
            tmpArray[ tmpPos++ ] = a[ leftPos++ ];

        while( rightPos <= rightEnd )  // Copy rest of right half
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        // Copy tmpArray back
        for( int i = 0; i < numElements; i++, rightEnd-- )
            a[ rightEnd ] = tmpArray[ rightEnd ];
    }


    public static int[] quickSort(int[] array) {
        // Write your solution here
        if(array == null){
            return array;
        }

        quickSort(array, 0, array.length-1);

        return array;
    }

    private static void quickSort(int[] array, int left, int right){
        if(left >= right){
            return;
        }

        int pivotIndex = median3 (array, left, right);
        int pivot = array[pivotIndex];

        swap(array, pivotIndex, right);

        int i = left;
        int j = right -1;

        while (i <= j){
            if(array[i] < pivot) {
                i++;
            }else if(array[j] >= pivot){
                j--;
            }else{
                swap(array,i,j);
                i++;
                j--;
            }
        }
        swap (array,i,right);

        quickSort(array, left, i-1);
        quickSort(array, i +1, right);
    }

    private static void swap(int[] array, int left, int right){
        int temp = array [left];
        array [left] = array [right];
        array [right] = temp;
    }

    private static int median3 (int[] array, int left, int right){
        int center = (left + right)/2;
        if (array[center] < array[left]){
            swap(array,left,center);
        }
        if(array[left] > array[right]){
            swap(array,left,right);
        }
        if(array[center] > array[right]){
            swap(array,center,right);
        }
        return center;
    }



    public static int[] rainbowSort(int[] array) {
        // Write your solution here.
        if(array.length == 0){
            return array;
        }

        int i = 0;
        int j = 0;
        int k= array.length - 1;

        while (j <= k){
            if(array[j] == -1){
                swap (array, i++, j++);
            }else if (array[j] == 0){
                j++;
            }else{
                swap(array, j, k--);
            }
        }
        return array;
    }

    public static int[] moveZero(int[] array) {
        // Write your solution here.
        if(array.length == 0){
            return array;
        }
        moveZero(array, 0, array.length -1);
        return array;
    }
    private static void moveZero(int[]array, int left, int right){
        int i = left;
        int j= right;
        while (i < j){
            if(array[i] == 0){
                swap (array, i, j--);
            }else {
                i++;
            }
        }
    }


}







