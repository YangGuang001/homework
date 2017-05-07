/**
 * Created by yz on 2017/5/7.
 */
public class Node {
    private char data;
    private Node lChild;
    private Node rChild;

    public Node(char data,Node lChild,Node rChild){
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public Node getlChild() {
        return lChild;
    }

    public void setlChild(Node lChild) {
        this.lChild = lChild;
    }

    public Node getrChild() {
        return rChild;
    }

    public void setrChild(Node rChild) {
        this.rChild = rChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", lChild=" + lChild +
                ", rChild=" + rChild +
                '}';
    }
}
