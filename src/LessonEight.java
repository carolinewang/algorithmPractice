import java.util.*;

/**
 * Created by haylin2002 on 2/1/16.
 */
public class LessonEight {
    public static void main(String[] args) {
//        System.out.println(permutations("aac"));
//        System.out.println(permutations(null));
//        System.out.println(reverseWords("I love Google"));
//        System.out.println(replace("buy_price_45", "_", "20%"));
//        System.out.println(rightShift("abcdefg", 39));
        System.out.println(allAnagrams("dog", "a"));
    }

    public static List<String> permutations(String set) {
        // Write your solution here.
        List<String> result = new ArrayList<>();
        if (set == null || set.isEmpty()){
            return result;
        }
        char[] array = set.toCharArray();
        permutations(array,result,0);
        return result;
    }
    private static void permutations(char[] array, List<String> result, int index){
        if(index == array.length){
            result.add(new String(array));
            return;
        }
        Set<Character> hashSet = new HashSet<>();
        for(int i = index; i < array.length; i++){
            //use a hashSet to check if there are duplicate chars, if so, do nothing
            if(!hashSet.contains(array[i])){
                hashSet.add(array[i]);
                swap(array,index,i);
                permutations(array,result,index + 1);
                //remember to swap back
                swap(array,index,i);
            }
        }
    }
    private static void swap(char[]array,int l, int r){
        char tmp = array[l];
        array[l] = array[r];
        array[r] = tmp;

    }

    /**
     * Reverse the words in a sentence.
     * Assumptions
     * Words are separated by single space
     * There are no heading or tailing white spaces
     * Examples
     * “I love Google” → “Google love I”
     * Corner Cases
     * If the given string is null, we do not need to do anything.
     */
    public static String reverseWords(String input) {
        // Write your solution here.
        if(input == null){
            return null;
        }
        char[] array = input.toCharArray();
        //first reverse the whole sentence
        reverse(array, 0, array.length - 1);
        //second reverse each word
        int start = 0;
        //end stops at space
        for(int end = 0; end < array.length; end++){
            if(array[end] == ' ') {
                reverse(array, start, end - 1);
                start = end + 1;
            }
            //special case for the last word since there's no space forward
            if(end == array.length - 1){
                reverse(array, start, end);
            }
        }
        return new String(array);
    }

    private static void reverse(char[]array,int i, int j){
        while(i < j){
            swap(array,i,j);
            i++;
            j--;
        }
    }
/**
 * Laicode Class 08
 * Given an original string input, and two strings S and T, replace all occurrences of S in input with T.
 * Assumptions:
 * input, S and T are not null, S is not empty string
 * Examples:
 * input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
 * input = "dodododo", S = "dod", T = "a", input becomes "aoao"
 */
    public static String replace(String input, String s, String t) {
        // Write your solution here.
        if(input == null || input.isEmpty() || s == null || t == null){
            return input;
        }
        char[] array = input.toCharArray();
        if(t.length() <= s.length()){
            return replaceShorter(array, s, t);
        }else{
            return replaceLonger(array, s, t);
        }
    }
    public static String replaceShorter(char[] input, String s, String t){
        //left to slow pointer is the result
        //fast to beyond is the area to be explored
        int slow = 0;
        int fast = 0;
        while(fast < input.length){
            //when we find an occurance of the pattern, we replace it with the new str
            //note it's <=, not <, otherwise if the word is in the end of the string, it won't be found!!!
            if(fast <= input.length - s.length() && equalSubStr(input, fast, s)){
                copyNewStr(input, slow, t);
                slow += t.length();
                fast += s.length();
            }else{
                input[slow++] = input[fast++];
            }
        }
        return new String(input, 0, slow);
    }

    private static boolean equalSubStr(char[]input, int fromIndex, String s){
        for(int i = 0; i < s.length(); i++){
            if(input[fromIndex + i] != s.charAt(i)){
                return false;
            }
        }
        return true;
    }
    private static void copyNewStr(char[]input, int fromIndex, String s){
        for(int i = 0; i < s.length(); i++){
            input[fromIndex + i] = s.charAt(i);
        }
    }

    public static String replaceLonger(char[]input, String s, String t){
        List<Integer> matches = getAllMatches(input, s);
        //calculate the new array size
        int newSize = input.length + matches.size() * (t.length() -s.length());
        char[] array = new char[newSize];
        //the last index in the matches arraylist
        int lastMatchingIndex = matches.size() - 1;
        //slow is in the original array's last position
        int slow = input.length - 1;
        //fast is in the new array's last position
        int fast = array.length - 1;
        while(slow >= 0){
            if(lastMatchingIndex >= 0 && slow == matches.get(lastMatchingIndex)){
                copyNewStr(array, fast - t.length() + 1, t);
                fast -= t.length();
                slow -= s.length();
                lastMatchingIndex--;
            }else{
                array[fast--] = input[slow--];
            }
        }
        return new String(array);
    }
    private static List<Integer> getAllMatches(char[] input, String s){
        List<Integer> matches = new ArrayList<>();
        int i = 0;
        //note it's <=, not <, otherwise if the word is in the end of the string, it won't be found!!!
        while(i <= input.length - s.length()){
            if(equalSubStr(input, i, s)){
                //record the ending index instead of start index for further convenience
                matches.add(i + s.length() - 1);
                i+= s.length();
            }else{
                i++;
            }
        }
        return matches;
    }

    public static String rightShift(String input, int n) {
        // Write your solution here.
        if(input == null || input.isEmpty() || n < 0 ){
            return input;
        }
        char[] array = input.toCharArray();
        int move = n % array.length;
        reverse(array, 0, array.length - 1);
        reverse(array, 0, move - 1);
        reverse(array, move, array.length - 1);
        return new String(array);
    }

    private static void reverse1(char[] array, int i, int j){
        while (i < j){
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }

    public static List<Integer> allAnagrams(String s, String l) {
        // Write your solution here.
        List<Integer> result = new ArrayList<Integer>();
        if(s == null || l == null || s.isEmpty() ||s.length() > l.length()){
            return result;
        }
        Map<Character,Integer> hashMap = new HashMap<>();
        //map the letters and each letter's occurence in String s into a hashtable
        char[] pattern = s.toCharArray();
        for(char letter : pattern){
            Integer count = hashMap.get(letter);
            if(count == null){
                hashMap.put(letter,1);
            }else{
                hashMap.put(letter,count + 1);
            }
        }
        int slow = 0;
        int fast = s.length() - 1;
        char[] array = l.toCharArray();
        Map<Character,Integer> window = new HashMap<>();
        while(fast < array.length){
            int match = 0;
            //for the first index, need to add all the letters into the window hash table
            if(slow == 0){
                for(int i = slow; i <= fast; i++){
                    //put the letters in the current window into a window hashMap
                   addToMap(window,array,i);
                }
                //for the rest, just move the sliding window to the right by one, so only need to remove the prev one,
                //and adding the next one
            }else if(slow > 0){
                //update the count for the prev letter after the sliding window moves to the right
                if(window.containsKey(array[slow - 1])) {
                    Integer prev = window.get(array[slow - 1]);
                    window.put(array[slow - 1], prev - 1);
                }
                //adding the new letter
                addToMap(window,array,fast);
            }
            //for each letter in the sliding window, check if the hashMap has the same letter
            for(int i = slow; i <= fast; i++) {
                if (hashMap.containsKey(array[i]) && hashMap.get(array[i]) >= window.get(array[i])) {
                    match++;
                }
            }
            if(match == s.length()){
                result.add(slow);
            }
            slow++;
            fast++;
        }
        return result;
    }
    private static void addToMap(Map<Character,Integer> window,char[] array, int i){
        Integer windowCount = window.get(array[i]);
        if(windowCount == null){
            window.put(array[i],1);
        }else{
            window.put(array[i],windowCount + 1);
        }
    }
}
