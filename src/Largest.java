import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haylin2002 on 3/6/16.
 */
public class Largest {
    public static void main (String[] args){
        int[] array = new int[]{3,5,2,2,1,6,3,4,5};
        System.out.print(Arrays.toString(largestAndSecond(array)));
    }
    static class Element{
        int value;
        List<Integer> comparedValues;

        Element(int value){
            this.value = value;
            comparedValues = new ArrayList<>();
        }
    }
    public static int[] largestAndSecond(int[] array) {
        // Write your solution here.
        // The first element is the largest number,
        // the second element is the second largest number.

        Element[] helper = convert(array);
        int largerLength = array.length;
        //if only one element, that's the largest one
        while(largerLength > 1){
            compareAndSwap(helper, largerLength);
            largerLength = (largerLength + 1) / 2;
        }
        return new int[]{helper[0].value, largest(helper[0].comparedValues)};
    }
    private static Element[] convert(int[] array){
        Element[] helper = new Element[array.length];
        for(int i = 0; i < array.length; i++){
            helper[i] = new Element(array[i]);
        }
        return helper;
    }
    private static void compareAndSwap(Element[] helper, int largerLength){
        for(int i = 0; i < largerLength / 2; i++){
            if(helper[i].value < helper[largerLength - 1 - i].value){
                swap(helper, i, largerLength - 1 - i);
            }
            helper[i].comparedValues.add(helper[largerLength - 1 - i].value);
        }
    }
    private static void swap(Element[] helper, int left, int right){
        Element tmp = helper[left];
        helper[left] = helper[right];
        helper[right] = tmp;
    }
    private static int largest(List<Integer> list){
        int largest = list.get(0);
        for(int num :list){
            if(num > largest){
                largest = num;
            }
        }
        return largest;
    }
}
