import java.util.ArrayList;
import java.util.List;

/**
 * Created by haylin2002 on 1/26/16.
 */
public class LessonSix {
    public static void main(String[] args) {
        System.out.println(vavlidParentheses(3));
//        System.out.println(combinations(99,new int[]{25,10,5,1}));
        System.out.println(combinations(120000,new int[]{100,50,25,10,1}));

    }

    public List<String> subSets(String set) {
        // Write your solution here.
        List<String> result = new ArrayList<String>();
        if (set == null || set.equals(" ")){
            return result;
        }
        char[] arraySet = set.toCharArray();

        StringBuilder sb = new StringBuilder();
        helper(arraySet, sb, 0, result);
        return result;
    }
    private void helper(char[]set, StringBuilder sb, int index, List<String> result){
        if(index == set.length){
            result.add(sb.toString());
            return;
        }
        //case 1
        sb.append(set[index]);
        helper(set, sb, index + 1, result);
        sb.deleteCharAt(sb.length() - 1);
        //case 2
        helper(set, sb, index + 1, result);
    }

    public static List<String> vavlidParentheses(int n) {
        // Write your solution here.
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        validParentheses(n, 0, 0, result, sb);
        return result;
    }


    private static void validParentheses (int n, int left, int right, List<String> result, StringBuilder sb){
        if(left + right == 2*n){
            result.add(sb.toString());
            return;
        }
        if(left < n){
            sb.append("(");
            validParentheses(n, left + 1, right, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(left > right){
            sb.append(")");
            validParentheses(n, left, right + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static List<List<Integer>> combinations(int target, int[] coins) {
        // Write your solution here.
        List<List<Integer>> result = new ArrayList<>();
        // //corner cases
        // if (coins == null || coins.length == 0 || target < 0){
        //   return result;
        // }
        combinations(target, coins, 0, new ArrayList<Integer>(), result);
        return result;
    }
    //balance is the money remaining
    //int[] coins are the coin denomination
    //num stores the number of coins for each denomination
    private static void combinations(int balance, int[] coins, int index, List<Integer> num,
                              List<List<Integer>> result){
        //when we have tried every coin denomination, return the result
        if(index == coins.length - 1){
            //here it has to be a nested if statement, because once the index is reached
            //to the end, we have to return
            //check if the remaining balance can be made up by the remaining coins,
            //if yes, add it to the result; if not, return and go back to the previous level
            if(balance % coins[index] == 0){
                num.add(balance / coins[index]);
                result.add(new ArrayList<Integer>(num));
                //remember to remove the last num when back tracking to the previous level
                num.remove(num.size() - 1);
            }
            return;
        }
        //determine how many branches with the current coin value
        int branches = balance / coins[index];
        //recursively calculate for each branch
        for (int i = 0; i <= branches; i++){
            num.add(i);
            combinations(balance - i*coins[index], coins, index + 1, num, result);
            num.remove(num.size() - 1);
        }
    }
}
