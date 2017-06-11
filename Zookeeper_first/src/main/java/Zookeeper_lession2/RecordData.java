package Zookeeper_lession2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yz on 2017/6/11.
 */
@Data
@AllArgsConstructor
public class RecordData implements Serializable{
    private static final long serivalVersonUID = 4260577459043203630L;
    private long cid;
    private String name;
}
