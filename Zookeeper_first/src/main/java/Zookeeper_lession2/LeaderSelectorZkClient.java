package Zookeeper_lession2;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yz on 2017/6/11.
 */
public class LeaderSelectorZkClient {
    public static void main(String[] args) throws Exception{
        List<ZkClient> zkList = new LinkedList<ZkClient>();
        List<MasterSelected> masterList = new LinkedList<MasterSelected>();
        try {
            for (int i = 0; i < 10; i++) {
                ZkClient zkClient = new ZkClient("localhost:2181", 5000, 5000, new SerializableSerializer());
                zkList.add(zkClient);
                RecordData recordData = new RecordData(Long.valueOf(i), "Client #" + i);
                MasterSelected masterSelected = new MasterSelected(zkClient, recordData);
                masterList.add(masterSelected);
                masterSelected.start();
            }

            System.out.println("敲回车退出！！");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }finally {
            System.out.println("shutdown ......");

            for (MasterSelected masterSelected : masterList){
                masterSelected.stop();
            }

//            for (ZkClient zkClient : zkList){
//                zkClient.close();
//            }
        }
    }
}
