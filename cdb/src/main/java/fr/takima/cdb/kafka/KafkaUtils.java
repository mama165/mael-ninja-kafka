package fr.takima.cdb.kafka;


import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.utils.Time;
import scala.collection.Seq;
import scala.collection.convert.Wrappers;

import java.io.IOException;
import java.util.Properties;


public class KafkaUtils {

    public static void main(String... args) {
        System.out.println("Hello world");
        initKafkaClient();

    }

    public static void initKafkaClient() {
        Properties adminProps = new Properties();

        String zookeeperHost = "localhost:2181"; // Il faut mettre localhost en local à la place de "zookeeper" !!!
        Boolean isSucre = false;
        int sessionTimeoutMs = 200000;
        int connectionTimeoutMs = 15000;
        int maxInFlightRequests = 10;
        Time time = Time.SYSTEM;
        String metricGroup = "myGroup";
        String metricType = "myType";
        KafkaZkClient zkClient = KafkaZkClient.apply(zookeeperHost, isSucre, sessionTimeoutMs,
                connectionTimeoutMs, maxInFlightRequests, time, metricGroup, metricType);

        AdminZkClient adminZkClient = new AdminZkClient(zkClient);

            Seq<String> allTopic = zkClient.getAllTopicsInCluster();

            for(int i = 0; i < allTopic.length(); i++){
                System.out.println(((Wrappers.JListWrapper) allTopic).underlying().get(i));
            }

            System.out.println("Cluster has " + allTopic.length() + " topics");

    }
}

