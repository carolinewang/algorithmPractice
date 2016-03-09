import java.util.Deque;
import java.util.LinkedList;

public class StackWithMin {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public StackWithMin() {
        // write your solution here
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public int pop() {
        if(stack.isEmpty()){
            return -1;
        }else{
            int result = stack.pollFirst();
            if(minStack.peekFirst() == result){
                minStack.pollFirst();
            }
            return result;
        }
    }

    public void push(int element) {
        stack.offerFirst(element);
        if(minStack.isEmpty() || element <= minStack.peekFirst()){
            minStack.offerFirst(element);
        }
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.peekFirst();
    }

    public int min() {
        return stack.isEmpty() ? -1 : minStack.peekFirst();
    }

}
