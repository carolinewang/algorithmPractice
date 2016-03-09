/**
 * Created by haylin2002 on 2/11/16.
 */
public class BitOperation {
    public static void main (String[] args){
        System.out.println(diffBits(5,-8));
        System.out.println(allUnique("background"));
        System.out.println(allUnique("apple"));
        System.out.println(allUnique("aa"));
        System.out.println(allUnique("god"));
        System.out.println(hex(17));
    }
    public static int diffBits(int a, int b) {
        // Write your solution here.
        int count = 0;
        for(int c = a ^ b; c != 0; c >>>= 1){
            count += c & 1;
        }
        return count;
    }

    public static boolean allUnique(String word) {
        // write your solution here
        //用一个长度为8的int array来表示255个ASCII码字符，每个int里面存32字节，需要8个
        //如果有重复字符，代表该字符的int里的字节就变成1
        int[] a = new int[8];
        final int size = 32;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            //得到该字符的对应数字
//            int code = Character.getNumericValue(c);
            //该字符在int array中是第几个int，应该是除数
            int row = c / size;
            //得到第几个int之后，具体在哪一个字节，是余数
            int column = c % size;
            //这个int就是a[row]，把1往左shift和column数一样多的位数，使int整个与它对齐，做与操作
            //因为与操作是两个位都为1时，结果才是1，所以如果原来是1，那么结果也是1，就说明已经出现过了这个字符
            //但是要注意必须写成不等于0，而不是等于1，因为你是比较该位上的是不是0或1，可是整体整个int可能是一个乱七八糟的0100010的数，
            //即使这一位是1，也不能说整体就等于1，所以应该说不等于0，只要不等于0了，就说明某位有1存在，就是false了
            if((a[row] & (1 << column)) != 0){
                return false;
            }
            //如果还没有出现过这个字符，就把它标记为1，把它和1对齐后，做或操作(只要有一个位是1，就是1），所以就可以强制转成1
            a[row]|= (1 << column);

        }
        //如果当中没有出现false情况，就说明都是unique的，就可以返回true了
        return true;
    }

    public static String hex(int number) {
        // Write your solution here.
        final char[] map = new char[]{0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F'};
        final String prefix = "0x";
        if(number == 0){
            return prefix + number;
        }
        StringBuilder sb = new StringBuilder();
        while (number > 0){
            int i = number % 16;
            sb.append(map[i]);
            number = number / 16;
        }
        return prefix + sb.reverse().toString();
    }
}
