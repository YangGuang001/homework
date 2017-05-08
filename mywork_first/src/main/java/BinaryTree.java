import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yz on 2017/5/7.
 */
@Data
public class BinaryTree {
    private String treeData;
    private Node root;


    public BinaryTree(Node root){
        this.root = root;
    }

    public BinaryTree(String treeData){
        this.treeData = treeData;
    }

    public Node createTree(){
        char[] data = treeData.toCharArray();
        List<Node> nodes = new LinkedList<Node>();
        Node rootNode = null;
        char child = 'N';
        int level = -1;
        Node now = null;
        for (int i=0; i < data.length; i++){
            switch (data[i]){
                case '(':
                    level++;
                    nodes.add(now);
                    child = 'L';
                    break;
                case ',':
                    child = 'R';
                    break;
                case ')':
                    nodes.remove(level);
                    level--;
                    break;
                default:
                    now = new Node(data[i],null,null);
                    if(rootNode == null){
                        rootNode = now;
                    }else{
                        switch (child){
                            case 'L':
                                nodes.get(child);
                                break;
                            case 'R':
                                nodes.get(level).setRChild(now);
                        }
                    }
            }
        }
        return rootNode;
    }
}
