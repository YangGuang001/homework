import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yz on 2017/5/7.
 */
@Data
@AllArgsConstructor
public class Node {
    private char data;
    private Node lChild;
    private Node rChild;
}
