package Zookeeper_lession2;

import lombok.Getter;
import lombok.Setter;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2017/6/11.
 */
public class MasterSelected {
    private volatile boolean runing = false;
    @Setter
    @Getter
    private ZkClient zkClient;
    private static final String MASTER_PATH = "/master";
    private IZkDataListener dataListener;
    private RecordData serverData;
    private RecordData masterData;
    private ScheduledExecutorService delayExector = Executors.newScheduledThreadPool(1);
    private int delayTime = 5;

    public MasterSelected(ZkClient zkClient, RecordData runningData){
        this.zkClient = zkClient;
        this.serverData = runningData;
        this.dataListener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                if (masterData != null && masterData.getName().equals(serverData.getName())){
                    takeMaster();
                }else {
                    delayExector.schedule(new Runnable() {
                        public void run() {
                            takeMaster();
                        }
                    },delayTime, TimeUnit.SECONDS);
                }
            }
        };
    }

    public void start() throws Exception{
        if (runing){
            throw new Exception("server has startup....");
        }

        runing = true;
        zkClient.subscribeDataChanges(MASTER_PATH,dataListener);
        takeMaster();
    }

    public void stop() throws Exception{
        if (!runing){
            throw new Exception("server has stopped.....");
        }
        runing = false;
        delayExector.shutdown();
        zkClient.unsubscribeDataChanges(MASTER_PATH,dataListener);
        zkClient.close();
    }

    private void takeMaster() {
        if (!runing)
            return;
        try {
            zkClient.create(MASTER_PATH,serverData, CreateMode.EPHEMERAL);
            masterData = serverData;
            System.out.println(serverData.getName() + " is master");

            delayExector.schedule(new Runnable() {
                public void run() {
                    if (checkMaster()){
                        relaseMaster();
                    }
                }
            },delayTime,TimeUnit.SECONDS);
        }catch (ZkNodeExistsException e){
            RecordData runningData = zkClient.readData(MASTER_PATH,true);
            if (runningData == null){
                takeMaster();
            }else {
                masterData = runningData;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void relaseMaster(){
        zkClient.delete(MASTER_PATH);
    }

    private boolean checkMaster(){
        try {
            RecordData runningData = zkClient.readData(MASTER_PATH);
            masterData = runningData;
            if (masterData.getName().equals(serverData.getName())){
                return true;
            }else{
                return false;
            }
        }catch (ZkNoNodeException e){
            return false;
        }catch (ZkInterruptedException e){
            return checkMaster();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
