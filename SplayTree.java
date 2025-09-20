import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

class SplayTree {
    Node root;

    public Node splay(long data,Node root) {
        if (root == null) return root;
        if (root.data == data) return root;

        Stack<Node> nodes = new Stack<>();
        Node node = root;
        while(true) {
            nodes.push(node);
            if (node == null || node.data == data) break;
            if (node.data < data) node = node.right;
            else node = node.left;
        }
//        System.out.println("Nodes: " + nodes);
        Node item = nodes.pop();
        if (item != null) {
//            System.out.println("item is: " + item);
            Node node1, node2 = null;
            while (!nodes.isEmpty()) {
                node1 = nodes.pop();
//                System.out.println("node1 is: " + node1);
                if (node2 != null) {
                    if (node1.right == node2) node1.right = item;
                    else node1.left = item;
                }
                if (!nodes.isEmpty()) node2 = nodes.pop();
                else node2 = null;
//                System.out.println("node2 is: " + node2);
                if (node2 != null) {
                    if (node1.right == item) {
                        if (node2.right == node1) { // RR
//                            System.out.println("Execute RR");
                            node2.right = node1.left;
                            node1.left = node2;
                            node1.right = item.left;
                            item.left = node1;
                        } else { // LR
//                            System.out.println("Execute LR");
                            node1.right = item.left;
                            item.left = node1;
                            node2.left = item.right;
                            item.right = node2;
                        }
                    } else {
                        if (node2.right == node1) { // RL
//                            System.out.println("Execute RL");
                            node1.left = item.right;
                            item.right = node1;
                            node2.right = item.left;
                            item.left = node2;
                        } else { // LL
//                            System.out.println("Execute LL");
                            node2.left = node1.right;
                            node1.right = node2;
                            node1.left = item.right;
                            item.right = node1;
                        }
                    }
//                    item = node2;
                } else {
                    if (node1.right == item) {  // R
//                        System.out.println("Execute R");
                        node1.right = item.left;
                        item.left = node1;
                    } else { // L
//                        System.out.println("Execute L");
                        node1.left = item.right;
                        item.right = node1;
                    }
//                    item = node1;
                }
            }
        } else item = root;

        return item;
    }

    public String createPathString(long data,Node root, String pathString) {
        if (root == null || root.data == data) return pathString;
        if (root.data > data) return createPathString(data, root.left, pathString + "L");
        else return createPathString(data, root.right, pathString + "R");
    }

    public Node add(long data){
        if (root == null) root = new Node(data);
        else {
            this.root = splay(data, root);
            if (root.data != data) this.root = splay(data, add(data, this.root));
        }
        return root;

    }
    public Node add( long data, Node root){
        Node node = new Node(data);
        while (true) {
            if (root.right == null && root.data < data) {
                root.right = node;
                break;
            } else if (root.left == null && root.data > data) {
                root.left = node;
                break;
            } else if (root.data < data) root = root.right;
              else if (root.data > data) root = root.left;
        }
        return this.root;
    }
    public void del(long data) {
        if (root != null) {
            root = splay(data, root);
            if (root.data == data) {
                if (root.left != null) {
                    Node temp = root.left;
                    Node temp2 = splay(findMax(temp).data, root.left);
                    temp2.right = root.right;
                    root = temp2;
                } else {
                    Node temp = root.right;
                    root = temp;
                }
            }
        }
    }
    public Node findMax(Node root){ //rast tarin zirderakht chap
        if (root.right != null) return findMax(root.right);
        return root;
    }
    public boolean find(long data) {
        if (root == null) return false;

        root = splay(data, root);
        boolean found = root.data == data;
        return found;
    }
    public long sum(long first,long last, Node root){
        if (root == null) return 0;
        if (root.data >= first && root.data <= last )
            return root.data + sum(first, last, root.left) + sum(first, last, root.right);
        if (root.data >= first) return sum(first, last, root.left);
        return  sum(first,last,root.right);
    }
    public void call(String instruction,SplayTree s){
        String [] ins = instruction.split(" ");
        if (ins[0].equals("add")){
            s.add(Long.parseLong(ins[1]));
        }else if (ins[0].equals("del")){
            s.del(Long.parseLong(ins[1]));
        } else if (ins[0].equals("find")) {
            System.out.println(s.find(Long.parseLong(ins[1])));
//            ans += a? "true" : "false";
        } else if (ins[0].equals("sum")) {
            System.out.println(s.sum(Long.parseLong(ins[1]),Long.parseLong(ins[2]),s.root));
        }
//        print2D(s.root);
//        return ans;
    }
    static void print2DUtil(Node root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += 5;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = 5; i < space; i++)
            System.out.print(" ");
        System.out.print(root.data + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

    // Wrapper over print2DUtil()
    static void print2D(Node root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
    public static void main(String[] args) {
//        Random generator = new Random();
//        int r = (int) generator.nextGaussian();
        try {
            System.setIn(new FileInputStream("input.txt"));
            System.setOut(new PrintStream(new FileOutputStream("output.txt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        SplayTree a = new SplayTree();
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        String finalans="";
        for (long i = 0; i <= n; i++) {
            a.call(sc.nextLine(),a);
//            if (ans.length()>0) finalans+=ans +((i<n) ? "\n" : "");
//            SplayTree.print2DUtil(a.root, 0);
//            System.out.println("------------------");
        }
        System.out.println(finalans);
    }

}

class Node{
    public long data;
    public Node right;
    public Node left;
    Node(long data){
        this.data=data;
    }

}
