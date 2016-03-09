import java.util.LinkedList;

/**
 * Created by haylin2002 on 1/12/16.
 */
public class QueueByTwoStack {
    //in stack to store elements
    private LinkedList<Integer> in;
    //out stack to output elements
    private LinkedList<Integer> out;

    public QueueByTwoStack(){
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    public Integer poll(){
        //move the elements from in stack to out stack first
        move();
        return out.isEmpty() ? null : out.pollFirst();
    }

    private void move(){
        //if out stack is empty, move elements from in stack
        if(out.isEmpty()){
            while(!in.isEmpty()){
                out.offerFirst(in.pollFirst());
            }
        }
    }

    public void offer(int value){
        in.offerFirst(value);
    }

    public Integer peek(){
        move();
        return out.isEmpty() ? null : out.peekFirst();
    }

    public int size(){
        return in.size() + out.size();
    }

    public boolean isEmpty(){
        return in.size() == 0 && out.size() == 0;
    }
}

