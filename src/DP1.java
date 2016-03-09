/**
 * Created by haylin2002 on 2/17/16.
 */
public class DP1 {
    public static void main (StringExcersice[] args){
        int[] array = new int[]{7, 2, 3, 1, 5, 8, 9, 6};
        int[] array2 = new int[]{1, 2, 3, 3, 4, 4, 5};
        int[] array3 = new int[]{1, 3, 2, 0, 3};
        int[] array4 = new int[]{4,2,1,2,0,0};
        int[] array5 = new int[]{2, 1, 1, 0, 2};
//        System.out.println(longest(array2));
//        System.out.println(maxProduct(5));
        System.out.println(canJump(array3));
        System.out.println(canJump(array4));
        System.out.println(canJump(array5));

    }
    public static int longest(int[] array) {
        // Write your solution here.
        //have a result array to store the length at each index
        //result[i] represents the length of the longest ascending subarray at this index (including current index)
        int[] result = new int[array.length];
        result[0] = 1;
        //max is used to store the current max length
        int max = 1;
        //scan the whole input array
        for(int i = 1; i < array.length; i++){
            //if the current value is larger than the prev one, we can add 1 to the length;
            //otherwise the current length is 1
            if(array[i] > array[i - 1]){
                result[i] = result[i-1] + 1;
            }else{
                result[i] = 1;
            }
            //compare the max with the current length, if larger, update the max value
            max = result[i] > max ? result[i] : max;
        }
        return max;
    }

    public static int maxProduct(int length) {
        // Write your solution here.
        if(length < 2){
            return length;
        }
        //result[i] represents the largest product with i-meter-long rope (At least one cut is made)
        int[] result = new int[length + 1];
        //when the rope is 2 meters long, the largest product is 1 ( 2 cuts, 1 * 1)
        result[2] = 1;
        for(int i = 3; i < result.length; i++){
            for(int j = 1; j < i; j++){
                //for each meter-long rope, try every possible cutting way to determine which one is the largest
                //e.g. 3-meter rope, three ways: 2-1 & 1-2 & 1-1-1
                //the second max is to take into consideration the case if no cut is made in the left section, for example,
                // 2 - 1 & 1- 2, no cut is made in the 2-meter section
                result[i] = Math.max(result[i], Math.max(i-j,result[i - j]) * j);
            }
        }
        return result[length];
    }

    public static boolean canJump(int[] array) {
        // Write your solution here.
        if(array == null || array.length == 0){
            return false;
        }
        boolean[] result = new boolean[array.length];
        result[array.length - 1] = true;
        for(int i = array.length - 2; i >= 0; i--){
            int step = array[i];
            //case 1: will not jump out of the array
            if(i + step <= array.length - 1){
                //check each step within the max jump distance, if one is true, then it's reachable
                for(int j = step; j >= 1; j--){
                    if(result[i + j]){
                        result[i] = true;
                        break;
                    }
                }
                //case 2: will jump out of the array, in this case, the end of the array is reachable
            }else{
                result[i] = result[array.length - 1];
            }
        }
        return result[0];
    }
}
