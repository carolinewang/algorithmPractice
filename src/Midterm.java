import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by haylin2002 on 2/6/16.
 */
public class Midterm {
    public static void main(StringExcersice[] args) {
        int[] A = new int[]{1,4,6,8,10};
        int[] B = new int[]{1,2,3,4};
        System.out.println(kthSmallestSumInTwoArrays(A,B,1));
    }
    public static int kthSmallestSumInTwoArrays(int[] A, int[] B, int k){
        if(A == null || B == null){
            return -1;
        }
        PriorityQueue<Cell> pq = new PriorityQueue<>(k, new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if(c1.value == c2.value){
                    return 0;
                }
                return c1.value > c2.value ? 1 : -1;
            }
        });

//来不及写了
        int m = A.length;
        int n = B.length;
        Set<Cell> visited = new HashSet<>();
        //the first cell in the table is empty
        Cell firstSum = new Cell(0,0,A[0] + B[0]);
        pq.offer(firstSum);
        visited.add(firstSum);
//        int sum = firstSum.value;
        for(int i = 0; i < k - 1; i++){
            Cell cur = pq.poll();
//            sum = cur.value;

            if (cur.column + 1 < m){
                Cell rightNei = new Cell(cur.column + 1 + 1, cur.row + 1, A[cur.column  + 1] + B[cur.row]);
                if(!visited.contains(rightNei)){
                    pq.offer(rightNei);
                    visited.add(rightNei);
                }
            }
            if(cur.row + 1 < n){
                Cell belowNei = new Cell(cur.column + 1, cur.row + 1 + 1, A[cur.column] + B[cur.row + 1]);
                if(!visited.contains(belowNei)){
                    pq.offer(belowNei);
                    visited.add(belowNei);
                }
            }
        }
        return pq.peek().value;
    }

}
