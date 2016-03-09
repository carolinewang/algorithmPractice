import java.util.ArrayList;
import java.util.List;

/**
 * Created by haylin2002 on 1/18/16.
 */
public class LessonFour {


     public static class TreeNode {
     public int key;
     public TreeNode left;
     public TreeNode right;
     public TreeNode(int key) {
     this.key = key;
     }
     }

    public static void main(StringExcersice[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        System.out.println(searchR(t1,1).key);
        System.out.println(insert(t3,5).key);
        System.out.println(insert(t3,2).key);
    }
    public class Solution {
        public TreeNode search(TreeNode root, int key) {
            // Write your solution here.
            if(root == null){
                return null;
            }
            if(root.key == key){
                return root;
            }else if(root.key < key){
                root.left = search(root.left,key);
            }else {
                root.right = search(root.right, key);
            }
            return null;
        }
    }

    public List<Integer> getRange(TreeNode root, int min, int max) {
        // Write your solution here.
        List<Integer> result = new ArrayList<Integer>();
        getRange(root,min,max,result);
        return result;
    }

    private void getRange(TreeNode root, int min, int max, List<Integer> list){
        if(root == null){
            return;
        }
        if(root.key < min){
            getRange(root.right,min,max,list);
        }else if (root.key > max){
            getRange(root.left,min,max,list);
        }else{
            getRange(root.left,min,max,list);
            list.add(root.key);
            getRange(root.right,min,max,list);
        }
    }
    public TreeNode search(TreeNode root, int target) {
        while (root != null) {
            if (root.key > target) {
                root = root.left;
            } else if (root.key < target) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
    public static TreeNode searchR(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
            if (root.key > target) {
                 return searchR(root.left,target);
            } else if (root.key < target) {
                 return searchR(root.right,target);
            } else {
                return root;
            }
//
    }

    public static TreeNode insert(TreeNode root, int t){
        while (root != null){
            if(t > root.key){
                root = root.right;
            }else if (t < root.key){
                root = root.left;
            }
        }
        return new TreeNode(t);
    }

}
