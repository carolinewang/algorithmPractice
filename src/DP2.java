import java.util.*;

/**
 * Created by haylin2002 on 2/19/16.
 */
public class DP2 {
    public static void main (String[] args){
        int[] array = new int[]{3,3,1,0,4};
        int[] array2 = new int[]{2,1,1,0,2};
        int[] array3 = new int[]{4,2,1,2,0,0};
        int[] array4 = new int[]{2,1,4,1,1,0};
        int[] array5 = new int[]{1,3,2,0,2};
        int[] array6 = new int[]{3,2,1,1,0};
        int[] array7 = new int[]{4,2,1,1,0,4};
        int[] array8 = new int[]{0};
        int[] array9 = new int[]{2,1,4,1,1,0};
        int[] array10 = new int[]{2,4,3,1,3,1,1,1,2};
        int[] array11 = new int[]{4,2,1,2,0,0};
        int[] array12 = new int[]{2,1,0,3,1};
        int[] array13 = new int[]{1,2,0};
        String word = "robob";
        String word2 = "robbobcat";
        String[] dict = new String[]{"bob", "cat", "rob"};

//        System.out.println(minJump(array));
//        System.out.println(minJump(array2));
//        System.out.println(minJumpII(array3));
//        System.out.println(minJumpII(array11));
//        System.out.println(minJumpII(array12));
//        System.out.println(minJumpIII(array13));
//        System.out.println(minJumpOut(array5));
//        System.out.println(minJumpOut(array6));
//        System.out.println(minJumpOut(array7));
//        System.out.println(minJumpOut(array8));
//        System.out.println(minJumpOut(array9));
//        System.out.println(minJumpOut(array11));
//        System.out.println(canBreak(word,dict));
//        System.out.println(canBreak(word2,dict));
        System.out.println(editDistance("sigh","asith"));
        System.out.println(editDistance("saturday","sunday"));

    }

    // Array Hopper II
    public static int minJump(int[] array) {
        // Write your solution here.
        if(array == null || array.length == 0){
            return -1;
        }
        int[] result = new int[array.length];
        boolean[] canJump = new boolean[array.length];
        result[array.length - 1] = 0;
        canJump[array.length - 1] = true;
        for(int j = array.length - 2; j >= 0; j--) {
            int step = array[j];
            if (j + step < array.length) {
                int min = result[j + 1];
                for (int i = 1; i <= step; i++) {
                    if (canJump[i + j]) {
                        canJump[j] = true;
                    }
                    min = result[j + i] < min ? result[j + i] : min;
                }
                result[j] = 1 + min;
            } else {
                //will jump out of array
                canJump[j] = true;
                result[j] = 1;
//                for (int i = 1; i <= array.length - j - 1; i++) {
//                    result[j] = 1 + Math.min(result[j], result[j + i]);
//                }
            }
        }
            return canJump[0] ? result[0] : -1;
        }
    //laicode answer, wrong
    public static int minJumpII(int[] array) {
        // Write your solution here.
        if(array == null || array.length == 0){
            return -1;
        }
        //scan from left to right
        int[] result = new int[array.length];
        result[0] = 0;
        for(int i = 1; i < array.length; i++){
            //initialize as unreachable
            result[i] = -1;
            //look at each of the previous indices before i
            for(int j = i - 1; j >= 0; j--){
                //if the jumping distance is enough to reach i from j, we update the result[i], otherwise it's unreachable (-1)
                if(j + array[j] >= i && result[j] != -1){
                    //if it doesn't have a value yet, we directly update it
                    //if it already has a value, we calculate the min by comparing the current value with the previous step + 1
                    if(result[i] == -1 || result[i] > result[j] + 1){
                        result[i] = result[j] + 1;
                    }
                }
            }
        }
        return result[array.length - 1];
    }



    //
//    public static int minJumpOutII(int[] array) {
//        // Write your solution here.
//        if(array == null || array.length == 0){
//            return -1;
//        }
//        int n = array.length;
//        int[] result = new int[n + 1];
//        boolean[] canJump = new boolean[n + 1];
//        result[n] = 0;
//        canJump[n] = true;
//        for(int j = n - 1; j >= 0; j--) {
//            int step = array[j];
//            if (j + step < n) {
//                int min = result[j + 1];
//                for (int i = 1; i <= step; i++) {
//                    if (canJump[i + j]) {
//                        canJump[j] = true;
//                    }
//                    min = result[j + i] < min ? result[j + i] : min;
//                }
//                result[j] = 1 + min;
//            } else {
//                //will jump out of array
//                canJump[j] = true;
//                for (int i = 1; i <= n - j - 1; i++) {
//                    result[j] = 1 + Math.min(result[j], result[j + i]);
//                }
//            }
//        }
//        return canJump[0] ? result[0] : -1;
//    }


    public static boolean canBreak(String input, String[] dict) {
        // Write your solution here.
        if(input == null || dict == null || input.length() == 0 || dict.length == 0){
            return false;
        }
        int n = input.length();
        boolean[] result = new boolean[n + 1];
        result[0] = true;
        Set<String> dictSet = toSet(dict);
        for(int i = 1; i < result.length; i++){
            for(int j = 0; j < i; j++){
                if(result[j] && dictSet.contains(input.substring(j,i))){
                    result[i] = true;
                    //remember to break, only one case matches is enough, no need for eatra loop
                    break;
                };
            }
        }
        return result[result.length - 1];
    }
    private static Set<String> toSet(String[] dict){
        Set<String> set= new HashSet<>();
        for(String s : dict){
            set.add(s);
        }
        return set;
    }

    //array hopper III
    public static int minJumpIII(int[] array) {
        // Write your solution here.
        //same as array hopper II
        if(array == null || array.length == 0){
            return -1;
        }
        //scan from left to right
        int[] result = new int[array.length];
        result[0] = 0;
        for(int i = 1; i < array.length; i++){
            //initialize as unreachable
            result[i] = -1;
            //look at each of the previous indices before i
            for(int j = i - 1; j >= 0; j--){
                //if the jumping distance is enough to reach i from j, we update the result[i], otherwise it's unreachable (-1)
                //need to check if result[j] is reachable, if itself is not even reachable, then there's no meaning to do the rest
                if(j + array[j] >= i && result[j] != -1){
                    //if it doesn't have a value yet, we directly update it
                    //if it already has a value, we calculate the min by comparing the current value with the previous step + 1
                    if(result[i] == -1 || result[i] > result[j] + 1){
                        result[i] = result[j] + 1;
                    }
                }
            }
        }

        //do a post processing. scan each index to see if from this index, can jump out of the array, if can, find the min
    int retVal = -1;
    for(int i = 0; i < result.length; i++) {
        //if the current index is reachable and also can jump out of array from here
        if(result[i] != -1 && array[i] >= result.length - i) {
            //if the value is not updated yet, update it; otherwise compare with the previous one to find the min
            if(retVal == -1 || retVal > result[i] + 1) {
                retVal = result[i] + 1;
            }
        }
    }

    return retVal;
}

    public static int editDistance(String one, String two) {
        // Write your solution here.
        //长度为one.length() + 1防止出现outOfBound
        int[][] result = new int[one.length() + 1][two.length() + 1];
        //initialize 00位置为0
        result[0][0] = 0;
        for(int i = 0; i<= one.length();i++){
            for(int j = 0; j <= two.length(); j++){
                //base case: 如果是string one里什么都没有，那么所需步数每次加1即可，也就是j
                if(i == 0){
                    result[i][j] = j;
                    //如果是string two什么都没有，那么所需步数每次加1即可，也就是i
                }else if(j == 0){
                    result[i][j] = i;
                    //如果i和j对应位置上是一样的，那么什么都不需要做，直接继承上一步的步数
                }else if(one.charAt(i - 1) == two.charAt(j - 1)){
                    result[i][j] = result[i - 1][j - 1];
                }else {
                    //否则的话分别计算做replace，delete, insert所需的步骤是多少
                    int replace = 1 + result[i - 1][j - 1];
                    int delete = 1 + result[i - 1][j];
                    int insert = 1 + result[i][j - 1];
                    //然后算出几种方法的最小值，注意先算出replace 和 delete的，赋值给它，再去和insert比较（因为Math.min一次只能比两个数）
                    //之所以不能直接四个一起比，是因为result[i][j]默认初始值就是0，会导致结果都是0
                    result[i][j] = Math.min(replace,delete);
                    result[i][j] = Math.min(insert,result[i][j]);
                }
            }
        }
        return result[one.length()][two.length()];
    }
}




