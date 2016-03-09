import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by haylin2002 on 1/20/16.
 */
public class LessonFive {
    static class Cell{
        int row;
        int column;
        int value;

        Cell(int row, int column, int value){
            this.row = row;
            this.column = column;
            this.value = value;
        }

    public int[] kSmallest(int[] array, int k) {
        // Write your solution here
        //corner cases
        if(array.length == 0 || k == 0){
            return new int[0];
        }
        //build a maxHeap with k elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
            //override the compare() so that it's a maxHeap
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1.equals(o2)){
                    return 0;
                }
                //note the comparison
                return o1 > o2 ? -1 : 1;
            }
        });
        for(int i = 0; i < array.length; i++){
            //insert the first k elements into the maxHeap
            if(i < k){
                maxHeap.offer(array[i]);
            }
            //for the remaining elements, if smaller than the max in the heap, then we need to replace it
            else if(array[i] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        //copy back the elements to the array from the end to the front so that the order is ascending
        int[] result = new int[k];
        for(int i = k - 1; i >= 0; i--){
            result[i] = maxHeap.poll();
        }

        return result;
    }
    }
}
