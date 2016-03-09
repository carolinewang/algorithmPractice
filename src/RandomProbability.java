import java.util.Arrays;
import java.util.Random;

/**
 * Created by haylin2002 on 3/3/16.
 */
public class RandomProbability {
    public static void main(StringExcersice[]args){
        int[]array = new int[]{1,2,3,4,5};

//        shuffle(array);
//        shuffle(array);
//        shuffle(array);
    }
    public static void shuffle(int[] array) {
        // Write your solution here.
        if(array == null || array.length == 0){
            return;
        }

        for(int i = array.length; i >= 1; i--){
            Random random = new Random();
            int idx = random.nextInt(i);
            swap(array,i-1,idx);
        }
        System.out.print(Arrays.toString(array));
    }
    private static void swap(int[]array,int left, int right){
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
