/**
 * Created by haylin2002 on 1/13/16.
 */
public class LessonThree {
    public static void main(StringExcersice[] args) {
        ListNode list = new ListNode(1);
//        System.out.print(numberOfNodes(list));

        System.out.println(insert(list,2).value);
        System.out.println(insert(list,4).value);
        System.out.println(insert(list, 3).value);

    }

    public static int numberOfNodes(ListNode head) {
        // write your solution here
        int count = 0;
        while(head != null){
            head = head.next;
            count++;
        }
        return count;
    }
    public static ListNode insert(ListNode head, int value){
        ListNode node = new ListNode(value);
        if(head == null || head.value >= value){
            node.next = head;
            return node;
        }
        ListNode prev = head;
        while(prev.next != null && value > prev.next.value){
            prev = prev.next;
        }
        ListNode oldNext = prev.next;
        prev.next = node;
        node.next = oldNext;
        return head;
    }

}
