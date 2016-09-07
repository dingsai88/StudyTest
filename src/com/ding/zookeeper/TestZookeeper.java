package com.ding.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat; 
public class TestZookeeper {
/**
    String connectionString="10.199.88.169:2181";
	      int connectionTimeout=50000;
	      
	      ZkClient zkClient=new ZkClient(connectionString, connectionTimeout);
	      
	      System.out.println(zkClient.getChildren("/taobao"));
	      
	      String Path="/thirdName";
	      
	   // 创建一个与服务器的连接
	      ZooKeeper zk = new ZooKeeper(connectionString, 
	    		  connectionTimeout, new Watcher() { 
	                 // 监控所有被触发的事件
	                 public void process(WatchedEvent event) { 
	                     System.out.println("已经触发了" + event.getType() + "事件！"); 
	                 } 
	             }); 
	      // 创建一个目录节点
	      zk.create(Path, "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
	        CreateMode.PERSISTENT); 
	      // 创建一个子目录节点
	      zk.create(Path+"/testChildPathOne", "testChildDataOne".getBytes(),
	        Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
	      System.out.println(new String(zk.getData(Path+"",false,null))); 
	      // 取出子目录节点列表
	      System.out.println(zk.getChildren(Path+"",true)); 
	      // 修改子目录节点数据
	      zk.setData(Path+"/testChildPathOne","modifyChildDataOne".getBytes(),-1); 
	      System.out.println("目录节点状态：["+zk.exists(Path+"",true)+"]"); 
	      // 创建另外一个子目录节点
	      zk.create(Path+"/testChildPathTwo", "testChildDataTwo".getBytes(), 
	        Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
	      System.out.println(new String(zk.getData(Path+"/testChildPathTwo",true,null))); 
	      // 删除子目录节点
	       zk.delete(Path+"/testChildPathTwo",-1); 
	      zk.delete(Path+"/testChildPathOne",-1); 
	      // 删除父目录节点
	      zk.delete(Path+"",-1); 
	      // 关闭连接
	      zk.close();
	      
	      
 * @param args
 */
	public static void main(String[] args) throws Exception {

	      
	  //    String Path="/thirdName/ALLBAIDULBS";
	//    String []aa=  "10.199.88.169;10.1.120.124".split(";");
	    
	   //   System.out.println(aa[0]+";"+aa[1]);
	    String connectionString="10.141.4.141:2181";
	      int connectionTimeout=50000;
	      
	 /*     ZkClient zkClient= new ZkClient(connectionString, connectionTimeout);
	      
	  //    System.out.println(zkClient.getChildren("/TAOBAO"));
	      System.out.println(zkClient.readData(Path+"/TAOBAO"));
	      
	      if(1==1){
		      return;

	      }*/
	      

	   // 创建一个与服务器的连接
	      ZooKeeper zk = new ZooKeeper(connectionString, 
	    		  connectionTimeout, new Watcher() { 
	                 // 监控所有被触发的事件
	                 public void process(WatchedEvent event) {
	                     System.out.println("已经触发了" + event.getType() + "事件！");
	                 }
	             });
	      // 创建一个目录节点
	   //   zk.create(Path, "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT); 
	      // 创建一个子目录节点
	    //   zk.create(Path, "testTAOBAO".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
	     // System.out.println(new String(zk.getData(Path+"",false,null))); 
	      // 取出子目录节点列表
	     // System.out.println(zk.getChildren(Path+"",true)); 
	      // 修改子目录节点数据
	    //  zk.setData(Path,"10.141.4.141".getBytes(),-1); 
	    //  System.out.println("目录节点状态：["+zk.exists(Path,true)+"]"); 
		//	Stat stat=zk.exists(Path,true);
	      
	      // 创建另外一个子目录节点
	    //  zk.create(Path+"/testChildPathTwo", "testChildDataTwo".getBytes(),  Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
	   //   System.out.println(Path+"值:"+new String(zk.getData(Path,true,null))); 
	      // 删除子目录节点
	   //    zk.delete(Path+"/testChildPathTwo",-1); 
	   //   zk.delete(Path+"/testChildPathOne",-1); 
	      // 删除父目录节点
	    //  zk.delete(Path+"",-1); 
	      // 关闭连接
	      zk.close();
	      

	      
	      
	      
	      
	      
	}

}
