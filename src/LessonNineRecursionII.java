import java.util.ArrayList;
import java.util.List;

/**
 * Created by haylin2002 on 2/10/16.
 */
public class LessonNineRecursionII {
    public static void main(String[] args) {
//        System.out.println(nQueens(8));
//        int[][]matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
//        int[][]matrix2 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        int[][]matrix3 = new int[][]{{1,2,3,4}};
//        System.out.println(spiralII(matrix3));
//        System.out.print(powerOfTwo(1025));
//        System.out.print(powerOfTwo(32));
//        System.out.print(powerOfTwo(8));
//        System.out.print(powerOfTwo(9));
        System.out.print(match("apple","2p2"));
        System.out.print(match("sophisticated","s11d"));


    }
    public static List<List<Integer>> nQueens (int n){
        //Assumptions: n > 0
        List<List<Integer>> result = new ArrayList<>();
        //cur is a list of size n, and cur[i] will be the column number where the queen on row i should be placed.
        List<Integer> cur = new ArrayList<>();
        int[] current = new int[n];
        helper(n, cur, result);
        return result;
    }
    private static void helper(int n, List<Integer> cur, List<List<Integer>> result){
        //base case: when all the rows are processed
        if(cur.size() == n){
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i = 0; i < n; i++){
            if(valid(cur,i)){
                cur.add(i);
                helper(n, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }
    private static boolean valid(List<Integer> cur, int column){
        int row = cur.size();
        for(int i = 0; i < row; i++){
            if(cur.get(i) == column || Math.abs(cur.get(i)- column) == row - i){
                return false;
            }
        }
        return true;
    }

    public static List<Integer> spiral(int[][] matrix) {
        // Write your solution here.
        List<Integer> result = new ArrayList<Integer>();
        spiral(matrix, 0, matrix.length, result);
        return result;
    }
    private static void spiral(int[][] matrix, int offset, int size, List<Integer> result){
        if(size == 0){
            return;
        }
        //base case
        if(size == 1){
            result.add(matrix[offset][offset]);
            return;
        }
        //the row on top
        for(int i = 0; i < size - 1; i++){
            result.add(matrix[offset][offset + i]);
        }
        //the right column
        for(int i = 0; i < size - 1; i++){
            result.add(matrix[offset + i][offset + size - 1]);
        }
        //the bottom row
        for(int i = size - 1; i >= 1; i--){
            result.add(matrix[offset + size - 1][offset + i]);
        }
        //the left column
        for (int i = size - 1; i >= 1; i--){
            result.add(matrix[offset + i][offset]);
        }
        //do the next layer of the spiral
        spiral(matrix, offset + 1, size - 2, result);
    }

    public static List<Integer> spiralII(int[][] matrix) {
        // Write your solution here.
        List<Integer> result = new ArrayList<Integer>();
        int m = matrix.length;
        if(m == 0){
            return result;
        }
        int n = matrix[0].length;
        spiralII(matrix, 0, m, n, result);
        return result;
    }
    private static void spiralII(int[][] matrix, int offset,int m, int n, List<Integer> result){
        //base case
        //if nothing left
        if(m == 0 || n == 0){
            return;
        }
        //base case: either one column or one row is left
        if(m == 1 || n == 1){
            if(m > n){
                for(int i = 0; i <= m; i++) {
                    result.add(matrix[offset + i][offset]);
                }
            }else if(m < n){
                for(int i = 0; i <= n; i++) {
                    result.add(matrix[offset][offset + i]);
                }
            }
            return;
        }
        //the row on top
        for(int i = 0; i < n - 1; i++){
            result.add(matrix[offset][offset + i]);
        }
        //the right column
        for(int i = 0; i < m - 1; i++){
            result.add(matrix[offset + i][offset + n - 1]);
        }
        //the bottom row
        for(int i = n - 1; i >= 1; i--){
            result.add(matrix[offset + m - 1][offset + i]);
        }
        //the left column
        for (int i = m - 1; i >= 1; i--){
            result.add(matrix[offset + i][offset]);
        }

        //do the next layer of the spiral
        spiralII(matrix, offset + 1, m - 2, n - 2, result);
    }

    public LessonFour.TreeNode lowestCommonAncestor(LessonFour.TreeNode root,
                                         LessonFour.TreeNode one, LessonFour.TreeNode two) {
        // Write your solution here.
        //base case
        if(root == null || root == one || root == two){
            return root;
        }
        //Step 1
        LessonFour.TreeNode left = lowestCommonAncestor(root.left, one, two);
        LessonFour.TreeNode right = lowestCommonAncestor(root.right, one, two);
        //Step 2
        //if both left and right are not null, return the current node
        if(left != null && right != null){
            return root;
        }
        return left != null ? left : right;
    }

    public static boolean match(String input, String pattern) {
        // Write your solution here.
        return match(input, pattern, 0, 0);
    }
    public static boolean match(String input, String pattern, int si, int pi){
        //base case
        if(si == input.length() && pi == pattern.length()){
            return true;
        }else if (si >= input.length() || pi >= pattern.length()){
            return false;
        }
        //case 1: first char of pattern is a digit
        if(isDigit(pattern.charAt(pi))){
            int num = 0;
            //get the num represented by all the digits
            while (pi < pattern.length() && isDigit(pattern.charAt(pi))){
                num = num * 10 + pattern.charAt(pi) - '0';
                pi++;
            }
            //check if the input is that long enough
            if(num > input.length()){
                return false;
            }else{
                return match(input, pattern, si + num, pi);
            }
        }else{
            //case 2: first char of pattern is not a digit
            if(input.charAt(si) == pattern.charAt(pi)){
                return match(input, pattern, si + 1, pi + 1);
            }
        }
        return false;
    }
    private static boolean isDigit(char input){
        return input >= '0' && input <= '9';
    }
}
