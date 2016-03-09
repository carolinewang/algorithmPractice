import java.util.*;

/**
 * Created by haylin2002 on 1/28/16.
 */
public class LessonSeven {

    public static void main(String[] args) {
//        String[] test = new String[]{"d","a","c","b","d","a","b","b","a","d","d","a","d"};
//        System.out.println(Arrays.toString(topKFrequent(test, 5)));
//        System.out.println(missingII(new int[]{8, 11, 10, 9, 4, 5, 6, 7, 2, 3, 1}));
//        System.out.println(missingII(new int[]{2, 1, 4}));
//        System.out.println(missingII(new int[]{1, 2, 3}));
//        System.out.println(missingII(new int[]{}));
//        List<Integer> A = null;
//        List<Integer> B = new ArrayList<>();
////        A.add(1);
////        A.add(2);
////        A.add(3);
////        B.add(1);
////        B.add(3);
////        B.add(4);
////        System.out.println(Arrays.toString(common(A, B).toArray()));
//        System.out.println(deDup("aabbbcccc"));
////        System.out.println(deDupII("aabbbccccZ"));
//        System.out.println(deDupII("abbc"));
//        System.out.println(strstr("", ""));
//        System.out.println(strstr("bcabc","ab"));
//        System.out.println(strstr("bcabc","bcd"));
//        System.out.println(strstr("abc",""));
//        System.out.println(strstr("","a"));
        System.out.println(removeSpaces(" I love Yahoo  "));
        System.out.println(removeSpaces(" "));

    }
    public static String[] topKFrequent(String[] combo, int k) {
        // Write your solution here.
        if(combo == null || combo.length == 0){
            return new String[0];
        }
        Map<String,Integer> freqMap = getFreqMap(combo);
        //store the key-value pair of word and its frequency into a minHeap with size k
        PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>(k,
                new Comparator<Map.Entry<String,Integer>>(){
                    @Override
                    public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
        for(Map.Entry<String,Integer> entry : freqMap.entrySet()){
            //for the first k elements, just insert them into the minHeap
            if(minHeap.size() < k){
                minHeap.offer(entry);
                //for the remaining entries, if larger than the current smallest one, replace the min
            }else if(entry.getValue() > minHeap.peek().getValue()){
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        //deleteMin k times and put them in an array from backwards so that the order is descending
        String[] result;
        //in case k is larger than the number of distinct words, just return all the distinct words
        if(k <= minHeap.size()){
            result = new String[k];
        }else {
            result = new String[minHeap.size()];
        }
        for(int i = minHeap.size() - 1; i >= 0; i--){
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }
    //put the string and frequency in a new HashMap, when we have a duplicate word, increase the freq
    private static Map<String,Integer> getFreqMap(String[] combo){
        Map<String,Integer> freqMap = new HashMap<>();
        for(String s : combo){
            Integer freq = freqMap.get(s);
            if(freq == null){
                freqMap.put(s,1);
            }else{
                freqMap.put(s,freq + 1);
            }
        }
        return freqMap;
    }

    public static int missing(int[] array) {
        // Write your solution here.
        if(array == null || array.length == 0){
            return 1;
        }
        int sum = 0;
        int n = array.length;
        int min = array[0];
        int max = array[n - 1];
        //maintain a consecutive boolean to check if the numbers are consecutive
        boolean consecutive = true;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            //we need the min and max to compute the correctSum if no num is missing
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }
        //if consecutive, the max value should be equal to min + (n - 1) * 1, otherwise it's not consecutive
        if(max != min + n - 1){
            consecutive = false;
        }
        //if consecutive and there's no 1 in the array, the missing num is 1
        if (consecutive && min != 1) {
            return 1;
            //if consecutive but the 1 is not missing, then the missing num is the the max num + 1
        } else if (consecutive) {
            return max + 1;
            //if not consective, the missing num is in the middle
        } else {
//        the correctSum for n - the current sum of n - 1 is the missing num. Note that the sum formula is for acsending order,
// but in this case we cannot gurantee the array is sorted, so we need to add min and max
            int correctSum = (min + max) * (n + 1) / 2;
            return correctSum - sum;
        }
    }

    //a better solution from the teacher
    public static int missingII(int[] array){
        //n 是array.length + 1比较好，这样不管是前面缺、当中缺还是末尾缺都可顾及到
        int n = array.length + 1;
        long targetSum = (long)(n * (n + 1) / 2);
        long actualSum = 0;
        for(int num : array){
            actualSum += num;
        }
        return (int)(targetSum - actualSum);
    }

    public static List<Integer> common(List<Integer> A, List<Integer> B) {
        // Write your solution here.
        int i = 0;
        int j = 0;
        List<Integer> result = new ArrayList<Integer>();
        while(i < A.size() && j < B.size()){
            if(A.get(i).equals(B.get(j))){
                result.add(A.get(i));
                i++;
                j++;
            }else if (A.get(i) < B.get(j)){
                i++;
            }else {
                j++;
            }
        }
        return result;
    }
    public static String deDup(String input) {
        // Write your solution here.
        if(input == null || input.isEmpty()){
            return input;
        }
        char[] array = input.toCharArray();
        //keep two pointers, the fast keeps going until there's a different char with the slow pointer
        //change the value of the slow to the fast
        int slow = 0;
        int fast = 1;
        while(fast < array.length){
            if(array[fast] != array[slow]){
                array[++slow] = array[fast];
                fast++;
            }else{
                fast++;
            }
        }
        //the chars before and including the slow pointer are the result
        //using the constructor to initialize a new string as the substring of the char array
        String result = new String(array, 0, slow + 1);
        return result;
    }

    public static String deDupII(String input) {
        // Write your solution here.
        if(input == null || input.isEmpty()){
            return input;
        }
        char[] array = input.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        int i = 0;
        while(i < array.length){
            char cur = array[i];
            if(stack.size() > 0 && array[i] == stack.peek()){
                while (i < array.length && cur == array[i]){
                    i++;
                }
                stack.pollFirst();
            }else{
                stack.offerFirst(array[i]);
                i++;
            }
        }
        int size = stack.size();
        array = new char[size];
        for (int j = 0; j < size; j++){
            array[j] = stack.pollFirst();
        }
        return new String(array);
    }

    public static int strstr(String large, String small) {
        // write your solution here
        if (large == null || small == null) {
            return -1;
        }
        if (small.isEmpty() && !large.isEmpty()) {
            return 0;
        }
        //keep trying every possible index,
        //only when the current index plus the length of the substring is still within the large string
        int j;
        for (int i = 0; i + small.length() <= large.length(); i++) {
            for (j = 0; j < small.length(); j++) {
                //i is the current index of the large string
                //j is the following index to compare which is synchronous with in the substring's index
                if (large.charAt(i + j) != small.charAt(j)) {
                    break;
                }
            }

            //when the characters in the substring have all been checked,
            //we return the index in the large string
            if (j == small.length()) {
                return i;
            }
        }
        return -1;
    }

    public static String removeSpaces(String input) {
        // Write your solution here.
        if(input.isEmpty() || input.equals(" ")){
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        int wordCount = 0;
        while(true){
            while(fast < array.length && array[fast] == ' '){
                fast++;
            }
            if(fast == array.length){
                break;
            }
            if(wordCount > 0){
                array[slow++] = ' ';
            }
            while(fast < array.length && array[fast] != ' '){
                array[slow++] = array[fast++];
            }
            wordCount++;
        }
        return new String(array, 0, slow);
    }

    public String remove(String input, String t) {
        // Write your solution here.
        if(input.isEmpty() || t.isEmpty()){
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < t.length(); i++){
            set.add(t.charAt(i));
        }
        while (fast < array.length){
            if(!set.contains(array[fast])){
                array[slow++] = array[fast++];
            }else{
                fast++;
            }
        }
        return new String(array, 0, slow);
    }
}
